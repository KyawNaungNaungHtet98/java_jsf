package com.mmit.admin.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.mmit.model.entities.User;
import com.mmit.model.entities.UserRole;
import com.mmit.model.service.UserService;

@Controller
public class AdminHomeController {
	@Autowired
	private UserService userService;

	@GetMapping("/admin")
	public String goIndex() {
		return "admin/index";
	}

	
	 @GetMapping("/admin/adminUser") 
	 public String userPage(Model m) { 
		 var role = UserRole.admin;
		 List<User> userList = new ArrayList<User>();
		 List<User> users = userService.findAll();
		 for (User user : users) {
			if(user.getRole() == role) {
				userList.add(user);
			}
		}
		 m.addAttribute("userList",userList);
		 
	 return "admin/user"; 
	 }
	 
	 @GetMapping("/admin/userEdit/{id}")
	 public String userEdit(@PathVariable("id") Integer id,Model m) {
		 var userList = userService.findById(id);
			m.addAttribute("user",userList);
		 
		 
		 return "admin/user-edit";
		 
	 }
	 @GetMapping("/admin/userAdd")
	 public String goAdd(Model m) {
		 m.addAttribute("user",new User());
			return "admin/user-edit";
		}
	 
	 @PostMapping("/admin/userSave")
	 public String saveUser(@ModelAttribute("user")User user,Model m) {
			
			
			var saveUser = userService.saveUser(user);
			 List<User> list = userService.findAll();
			 m.addAttribute("userList",list);
			 String redirectUrl = "/adminUser";
			  return "redirect:/admin" + redirectUrl;
		}
	 
	 @GetMapping("/admin/userDelete/{id}")
		public String deleteUser(@PathVariable("id") Integer id,Model m) {
			userService.deleteById(id);
			 List<User> list = userService.findAll();
			 m.addAttribute("userList",list);
			String redirectUrl = "/adminUser";
			return "redirect:/admin"+ redirectUrl;
		}
	 

}
