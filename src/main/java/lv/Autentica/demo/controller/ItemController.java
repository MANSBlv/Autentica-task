package lv.Autentica.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.Autentica.demo.models.Items;
import lv.Autentica.demo.service.ItemsService;

@Controller
public class ItemController {
	
	@Autowired
	ItemsService itemService;
	
	@GetMapping("/allItems")
	public String getAllItems (Model models) {
		/*
		 * gets all Items. Only admin can see
		 */
		models.addAttribute("Allitems", itemService.selectAllItems());
		return "all-items-page";
		
	}
	
	@GetMapping("/Items/{id}")
	public String getOneItem(@PathVariable long id, Model models) throws Exception {
		/*
		 * for the One item view
		 */
		models.addAttribute("item", itemService.getItemById(id));
		return "one-item-page";
	}
	
	@PostMapping("/items/addNew")
	public String insertItem (@Valid Items item, BindingResult result) {
		/*
		 * creates the item.
		 */
		if(!result.hasErrors()) {
			itemService.insertItem(item);
			return"redirect:/allItems";
		}else {
			return "item-add-page";
		}
	}
	
	@GetMapping("/items/addNew")
	public String getNewCreatedItem (Model models, Items items) {
		/*
		 * gets the created item
		 */
		Items item = new Items();
		models.addAttribute("createdItems", item);
		return "item-add-page";
	}
	
	@GetMapping("/update/item/{id}")
	public String getUpdatedItemById(Model models,@PathVariable(name = "id")long id ) {
		try {
			/*
			 * Gets the updated item.
			 */
			models.addAttribute("updateItem", itemService.getItemById(id));
			return "update-item-page";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error-page";
	}
	
	@PostMapping("/update/item/{id}")
	public String updateItemById (@PathVariable(name = "id")long id, @Valid Items item, BindingResult result) {
		/*
		 * updates Item by id, only admin can update it.
		 */
		if(!result.hasErrors()) {
			itemService.updateItemById(item, id);
			return"redirect:/allItems"; 
		}
		return "update-item-page";
		
	}
	
	@GetMapping("/delete/item/{id}")
	public String deleteItemById(@PathVariable(name = "id")long id ) throws Exception {
		/*
		 * Deletes items by Id, once item is deleted , ordered item is also deleted
		 */
		itemService.deleteItemById(id);
		
		return "redirect:/allItems";
		
	}

}
