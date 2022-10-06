package lv.Autentica.demo.service;

import java.util.ArrayList;

import lv.Autentica.demo.models.Items;

public interface ItemsService {

	public abstract boolean insertItem(Items item);
	
	public abstract boolean updateItemById(Items item, long id);
	
	public abstract void deleteItemById(long id) throws Exception;
	
	public abstract Items getItemById (long id) throws Exception;
	
	ArrayList<Items> selectAllItems();
	
}
