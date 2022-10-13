package com.mmit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.mmit.model.entities.User;
import com.mmit.model.entities.UserRole;
import com.mmit.model.service.CategorySerivce;
import com.mmit.model.service.ProductService;
import com.mmit.model.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private ProductService service;
	@Autowired
	private CategorySerivce cateService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "redirect:/shop";
		
	}
	@GetMapping("/shop")
	public String goShop(ModelMap map) {
		map.put("productList", service.findAll());
		map.put("categoryList", cateService.findAll());
		return "index";
	}
	@GetMapping("/shop/products/{id}")
	private String singleProduct(ModelMap map ,@PathVariable("id") long productId) {
		map.put("product", service.findById(productId));
		return "product-detail";
	}
	@GetMapping("/login")
	public String loginPage() {
		
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage(Model m) {
		m.addAttribute("user",new User());
		
		return "register";
	}
	@GetMapping("/shop/orders")
	public String myOrderPage() {
		return "my-order";
	}
	
	@PostMapping("/register/add")
	public String registerUser(@ModelAttribute("user")User user) {
		var newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setRole(UserRole.customer);
		newUser.setPhone(user.getPhone());
		newUser.setName(user.getName());
		newUser.setAddress(user.getAddress());
		userService.save(newUser);
		
		return "redirect:/shop";
	}
}
