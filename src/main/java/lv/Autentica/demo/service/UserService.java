package lv.Autentica.demo.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;

import lv.Autentica.demo.models.AddedItems;
import lv.Autentica.demo.models.User;

public interface UserService extends UserDetailsService{
	
	public abstract User getUserById (long id) throws Exception;
	
	ArrayList<User> getAllUsers();
	
	ArrayList<AddedItems> getAllAddedItemsByUsers();
	
	public abstract User findUserByEmail(String name);
	
	

}
