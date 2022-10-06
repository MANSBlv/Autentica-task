package lv.Autentica.demo.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lv.Autentica.demo.models.AddedItems;
import lv.Autentica.demo.models.Role;
import lv.Autentica.demo.models.User;
import lv.Autentica.demo.models.UserPrincipal;
import lv.Autentica.demo.repos.AddedItemsRepo;
//import lv.Autentica.demo.models.UserPrincipal;
import lv.Autentica.demo.repos.UserRepo;
import lv.Autentica.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AddedItemsRepo addedRepo;
	
	

	@Override
	public User getUserById(long id) throws Exception {
		/*
		 * gets all users. Didnt need to implement it in the end
		 */
		if(userRepo.existsById(id)) {
			User user = userRepo.findById(id).get();
			return user;
		}
		throw new Exception("User does not exist");
	}

	@Override
	public ArrayList<User> getAllUsers() {
		/*
		 * gets all users. Didnt need to implement it in the end
		 */
		return (ArrayList<User>) userRepo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 * for Spring security 
		 * process of finding the user
		 */
		User user = userRepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("invalid password or username");
		}
		return new UserPrincipal(user);
		
	}
	

	public boolean getUserEmailPassword(User users) {
		/*
		 * gets the users email and password and then check if raw password matches with hashed
		 */
		if(userRepo.existsByEmail(users.getEmail())) {
			User user = userRepo.findByEmail(users.getEmail());
			if(user.checkPassword(users.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	public User createUser(User user) {
		/*
		 * creates admin, since its a Log in only site, didnt have the need to implement it
		 */
		user.setRole(Role.ADMIN);
		user.setPasswordHashed(user.getPassword());
		return userRepo.save(user);
	}

	@Override
	public ArrayList<AddedItems> getAllAddedItemsByUsers() {
		/*
		 * gets all the added items by the user
		 */
		return (ArrayList<AddedItems>) addedRepo.findAll();
	}

	@Override
	public User findUserByEmail(String name) {
		/*
		 * find the users email
		 */
		if(userRepo.existsByEmail(name)) {
			User user = userRepo.findByEmail(name);
			
				return user;
			
		}
		return null;
	}

}
