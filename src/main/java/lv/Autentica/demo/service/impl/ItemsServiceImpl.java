package lv.Autentica.demo.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.Autentica.demo.models.Items;
import lv.Autentica.demo.repos.ItemsRepo;
import lv.Autentica.demo.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService{
	
	@Autowired
	private ItemsRepo itemRepo;
	

	@Override
	public boolean insertItem(Items item) {
		if(itemRepo.existsById(item.getEId())) {
			return false;
		}else {
			itemRepo.save(item);
			return true;
		}
		
	}

	@Override
	public boolean updateItemById(Items item, long id) {
		if(itemRepo.existsById(id)) {
			Items items = itemRepo.findById(id).get();
			items.setEquipmentName(item.getEquipmentName());
			items.setParameters(item.getParameters());
			itemRepo.save(items);
			return true;
		}
		return false;
	}

	@Override
	public void deleteItemById(long id) throws Exception {
		if(itemRepo.existsById(id)) {
			itemRepo.deleteById(id);
		}else {
			throw new Exception("Item does not exist");
		}
		
	}

	@Override
	public Items getItemById(long id) throws Exception{
		
		if(itemRepo.existsById(id)) {
			Items item = itemRepo.findById(id).get();
			return item;
		}
		throw new Exception("Item doesnt exist");
		
	}

	@Override
	public ArrayList<Items> selectAllItems() {
		
		return (ArrayList<Items>) itemRepo.findAll();
	}

}
