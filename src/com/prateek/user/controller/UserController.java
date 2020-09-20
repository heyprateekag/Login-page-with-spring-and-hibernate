package com.prateek.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prateek.user.dao.UserDAO;
import com.prateek.user.entity.User;

@Controller
//@RequestMapping("/user")
public class UserController {
	
	//need to inject the customer dao
	@Autowired
	private UserDAO userDAO;

	@RequestMapping("/")
	public String listUsers(Model theModel/*, HttpServletRequest request*/) {
		
		return "login";
		
	}

	//controller method to read form data and add data to the model
	@RequestMapping("/processLogin")
	public String processLoginPage(HttpServletRequest request, Model theModel) {
		
		//get users from the dao
				List<User> theUsers = userDAO.getUsers();
				
				String theEmail = request.getParameter("email");
				String thePassword = request.getParameter("password");
				
				System.out.println(theEmail + " " + thePassword);
				
				for(User tempUser : theUsers) {
					
					if(tempUser.getEmail().equals(theEmail)) {
						
						if(tempUser.getPassword().equals(thePassword)) {
							
							//add that user to the model
							theModel.addAttribute("user", tempUser);
							
							return "login-confirmation";
						}
						
					}
					
				}
		
		return "login";
	}
	
}
