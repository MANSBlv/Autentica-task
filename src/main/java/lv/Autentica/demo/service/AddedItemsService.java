package lv.Autentica.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import lv.Autentica.demo.models.AddedItems;
import lv.Autentica.demo.models.Items;
import lv.Autentica.demo.models.Status;
import lv.Autentica.demo.models.User;

public interface AddedItemsService {
	
	ArrayList<AddedItems> getAllAddedItems();
	
	ArrayList<AddedItems> getAllAddedItemsByUser(User user) throws Throwable;
	
	public abstract void deleteAddedItemById(long id,Items item) throws Throwable;
	
	public abstract boolean updateItemsStatusToAccept(long id, AddedItems items);
	
	ArrayList<AddedItems> selectAllOrderedItemsByStatus(Status status);
	
	boolean updateItemsStatusToDeny(long id, AddedItems items);
	
	AddedItems CreateOrder(AddedItems addedItems, User user, Items items);
	
	

}
