package lv.Autentica.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lv.Autentica.demo.models.AddedItems;
import lv.Autentica.demo.models.Items;
import lv.Autentica.demo.models.Status;
import lv.Autentica.demo.models.User;
import lv.Autentica.demo.repos.AddedItemsRepo;
import lv.Autentica.demo.repos.ItemsRepo;
import lv.Autentica.demo.service.AddedItemsService;
import lv.Autentica.demo.service.ItemsService;

@Controller
public class AddedItemController {

	@Autowired
	AddedItemsService addedService;
	@Autowired
	ItemsService itemService;
	@Autowired
	AddedItemsRepo addedItemRepo;
	@Autowired
	ItemsRepo repo;
	

	
	@GetMapping("/orderItems")
	public String getAllOrderedItemsByStatus(Model models, Status status) {
		/*
		 * Gets all ordered items by status Hold.
		 */
			models.addAttribute("orderedItems", addedService.selectAllOrderedItemsByStatus(status.HOLD));
			return "all-ordered-items-page";
		
	}
	

	@Transactional
	@RequestMapping(value="/orderItems/{id}",method = {RequestMethod.DELETE,RequestMethod.GET})
	public String DeleteOrderedItemById(@PathVariable(name = "id") long id, Items item) throws Throwable {
		/*
		 * Deletes ordered items by Id. 
		 * *Doesnt work fully*
		 */
		addedService.deleteAddedItemById(id,item);
		return "redirect:/orderItems";
	}
	
	@GetMapping("/orderItems/accept")
	public String getAllOrderedItemsByStatusAccept(Model models, Status status) {
		/*
		 * Gets all ordered items by status Accept.
		 */
			models.addAttribute("orderedItems", addedService.selectAllOrderedItemsByStatus(status.ACCEPT));
			return "all-ordered-items-page";
		
	}
	
	@GetMapping("/orderItems/deny")
	public String getAllOrderedItemsByStatusDeny(Model models, Status status) {
		/*
		 * Gets all ordered items by status Deny.
		 */
			models.addAttribute("orderedItems", addedService.selectAllOrderedItemsByStatus(status.DENY));
			return "all-ordered-items-page";
		
	}
	
	@GetMapping("/orderItems/Status/Deny/{id}")
	public String updateOrderStatusToDeny (@PathVariable(name = "id")long id, AddedItems items) {
		/*
		 * Updates order status to Deny
		 */
		if(addedService.updateItemsStatusToDeny(id, items) ) {
			
			return "redirect:/orderItems";
		}
		
		return "error";
	}
	
	@GetMapping("/orderItems/Status/Accept/{id}")
	public String updateOrderStatusToAccept (@PathVariable(name = "id")long id, AddedItems items) {
		/*
		 * Updates order status to Accept
		 */
		if(addedService.updateItemsStatusToAccept(id, items)) {
			
			return "redirect:/orderItems";
		}
 
		
		return "error";
	}
	
	@PostMapping("/createOrder")
	public String createOrder(@Valid AddedItems addedItems,@Valid User user ,Items item, BindingResult result) {
		/*
		 * Creates Orders. Only Regulars can create orders
		 */
			addedService.CreateOrder(addedItems, user, item);
			return "redirect:/orderItems";
		
	}
	
	@GetMapping ("/createOrder")
	String getCreatedOrder(Model models,  AddedItems addedItems) {
		/*
		 * Gets all the items for the user to choose from
		 * and let them create their order
		 */
		List<Items> items = itemService.selectAllItems();
		models.addAttribute("allItems", items);
		AddedItems item = new AddedItems();
		models.addAttribute("createOrderedItems", item);
		return "create-order-page";
	}
	
}
