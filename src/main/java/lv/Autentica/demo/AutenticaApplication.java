package lv.Autentica.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import lv.Autentica.demo.models.AddedItems;
import lv.Autentica.demo.models.Items;
import lv.Autentica.demo.models.Role;
import lv.Autentica.demo.models.Status;
import lv.Autentica.demo.models.User;
import lv.Autentica.demo.repos.AddedItemsRepo;
import lv.Autentica.demo.repos.ItemsRepo;
import lv.Autentica.demo.repos.UserRepo;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AutenticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticaApplication.class, args);
		
	}
	/*
	 * 
	 * 
	 * Used CommandLineRunner to add Entities to the database and see if the database works as intended
	 * Admin users credentials :
	 * email - admin@inbox.lv
	 * passwords - 123
	 * Regular Users credentials:
	 * email Mort@inbox.lv, Lion@inbox.lv
	 * both password - 123
	 * 
	 * No unit tests, had no time for it
	 */
	
//	@Bean
//	public CommandLineRunner runner (UserRepo userRepo, ItemsRepo itemsRepo, AddedItemsRepo addedRepo) {
//		return new CommandLineRunner() {
//			
//			@Override
//			public void run(String... args) throws Exception {
//				User user = new User("Lion@inbox.lv", "123", Role.REGULAR);
//				user.setPasswordHashed(user.getPassword());
//				//userRepo.save(user);
//				Items item = new Items("Mouse", "Razor");
//				//itemsRepo.save(item);
//				AddedItems addedItem = new AddedItems(new Date(), "need a mouse", new ArrayList<Items>(Arrays.asList(item)), user, Status.HOLD);
//				addedRepo.save(addedItem);
//				item.addAddedItems(addedItem);
//				itemsRepo.save(item);
//				//item.removeAddedItems(addedItem);
//				//itemsRepo.save(item);
//				
//			}
//		};
//	}

}
