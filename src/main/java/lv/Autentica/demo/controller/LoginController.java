package lv.Autentica.demo.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.Autentica.demo.models.Role;
import lv.Autentica.demo.models.Status;
import lv.Autentica.demo.models.User;
import lv.Autentica.demo.service.AddedItemsService;
import lv.Autentica.demo.service.UserService;
import lv.Autentica.demo.service.impl.UserServiceImpl;

@Controller
public class LoginController {
	@Autowired
	UserServiceImpl userImpl;
	@Autowired
	AddedItemsService addedService;
	@Autowired
	UserService userService;

	
	@RequestMapping("")
	public String viewHomePage(Model models, Status status, User user) {
		/*
		 * Using Springs Security already made log in page.
		 * Finds the user thats trying to log in and by their role directs them to their home page.
		 * Admins home page sees all the new orders .
		 * Regulars see all the added/ordered items .
		 */
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		user = userService.findUserByEmail(userDetails.getUsername());
		if(user.getRole()==Role.ADMIN) {
			models.addAttribute("orderedItems", addedService.selectAllOrderedItemsByStatus(status.HOLD));
			return "all-ordered-items-page";
		}else {
			models.addAttribute("orderedItems", addedService.getAllAddedItems());
			return "all-ordered-items-page";
		}
		
	}
	

	
	@GetMapping("/logout")
	public String logOut() {
		return "/login";
	}

}
