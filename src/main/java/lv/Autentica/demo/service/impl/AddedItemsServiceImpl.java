package lv.Autentica.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lv.Autentica.demo.models.AddedItems;
import lv.Autentica.demo.models.Items;
import lv.Autentica.demo.models.Role;
import lv.Autentica.demo.models.Status;
import lv.Autentica.demo.models.User;
import lv.Autentica.demo.models.UserPrincipal;
import lv.Autentica.demo.repos.AddedItemsRepo;
import lv.Autentica.demo.repos.ItemsRepo;
import lv.Autentica.demo.repos.UserRepo;
import lv.Autentica.demo.service.AddedItemsService;
import lv.Autentica.demo.service.UserService;
@Service
public class AddedItemsServiceImpl implements AddedItemsService {
	
	@Autowired
	private AddedItemsRepo addedRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ItemsRepo itemsRepo;
	
	private UserPrincipal principal;
	
	@Autowired
	UserService userService;

	@Override
	public ArrayList<AddedItems> getAllAddedItems() {
		/*
		 * gets all added items
		 */
		return (ArrayList<AddedItems>) addedRepo.findAll();
	}

	@Override
	public ArrayList<AddedItems> getAllAddedItemsByUser(User user) throws Throwable {
		/*
		 * Idea that each user could  only see all their ordered items and their status
		 * Not implemented
		 */
		if(userRepo.existsById(user.getIdUser())) {
			return (ArrayList<AddedItems>) user.getAddedItem();
		}else {
			throw new Exception().getCause();
		}
		
	}

	@Override
	public void deleteAddedItemById(long id,Items item)throws Throwable  {
		/*
		 * Deletes added item by Id
		 */

		if(addedRepo.existsById(id)) {
			
			AddedItems addedItems = addedRepo.findById(id).get();
			addedItems.removeItems(item);
			item.clearAddedItems(id);
			item.removeAddedItems(addedItems);
			addedRepo.save(addedItems);
			itemsRepo.save(item);
			addedRepo.deleteById(id);
		}else {
			throw new Exception().getCause();
		}
	}		


	@Override
	public boolean updateItemsStatusToAccept(long id, AddedItems items) {
		/*
		 * Statuses - HOLD, ACCEPT, DENY, DELETE
		 * updates Added Items status to Accept (Can interpret Added items as Ordered items) 
		 */
		if(addedRepo.existsById(id)) {
			AddedItems item = addedRepo.findById(id).get();
			item.setStatus(item.getStatus().ACCEPT);
			addedRepo.save(item);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateItemsStatusToDeny(long id, AddedItems items) {
		/*
		 * Statuses - HOLD, ACCEPT, DENY, DELETE
		 * updates Added Items status to Deny (Can interpret Added items as Ordered items) 
		 */
		if(addedRepo.existsById(id)) {
			AddedItems item = addedRepo.findById(id).get();
			item.setStatus(item.getStatus().DENY);
			addedRepo.save(item);
			return true;
		}
		return false;
	}



	@Override
	public ArrayList<AddedItems> selectAllOrderedItemsByStatus(Status status) {
		/*
		 * Statuses - HOLD, ACCEPT, DENY, DELETE
		 * Selects all Added items by their status (Can interpret Added items as Ordered items) 
		 */
		ArrayList<AddedItems> items = addedRepo.findByStatus(status);
		return items;
		
	}

	@Override
	public AddedItems CreateOrder(AddedItems addedItems,  User user, Items items) { 
		/*
		 * Create order function, where a current logged in User with Role.REGULAR can create an order. The order is automatically set on Hold and
			Since Items and AddedItems have ManyToMany  relation, both are saved.
		 */
		
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			user = userService.findUserByEmail(userDetails.getUsername());
			
			addedItems.setAddedDate(new Date());
			addedItems.setStatus(Status.HOLD);
				
			addedItems.addItems(items);
			items.addAddedItems(addedItems);
				
			addedItems.setUser(user);
	
			addedRepo.save(addedItems);
			
		return addedItems;
		
	}


}
