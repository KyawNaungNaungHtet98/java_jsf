package com.mmit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import com.mmit.model.entities.Category;
import com.mmit.model.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService cateService;
	
	@GetMapping("/category")
	public String goHome(Model m) {
		 List<Category> list = cateService.findAll();
		 m.addAttribute("categoryList",list);
		 
		return "category";
	}
	@GetMapping("/categoryAdd")
	public String addCategory(Model m) {
		m.addAttribute("category",new Category());
		return "category-add";
	}

}
