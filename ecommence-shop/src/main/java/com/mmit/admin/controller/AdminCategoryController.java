package com.mmit.admin.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mmit.model.entities.Category;
import com.mmit.model.service.CategorySerivce;


@Controller
public class AdminCategoryController {
	@Autowired
	private CategorySerivce cateService;
	@ModelAttribute
	public void assignDefualtModel(Model m) {
		m.addAttribute("page","product");
		m.addAttribute("Category",new Category());
		
	}
	@GetMapping("/admin/category")
	public String goHome(Model m) {
		 List<Category> list = cateService.findAll();
		 m.addAttribute("categoryList",list);
		 
		return "admin/category";
	}
	@GetMapping("/admin/categoryAdd")
	public String addCategory(Model m) {
		m.addAttribute("category",new Category());
		return "admin/category-add";
	}
	
	@PostMapping("/admin/categorySave")
	public String saveCategory(@ModelAttribute("category")Category category,Model m) {
		
		
		var saveCategory = cateService.save(category);
		 List<Category> list = cateService.findAll();
		 m.addAttribute("categoryList",list);
		 String redirectUrl = "/category";
		   return "redirect:/admin" + redirectUrl;
	}

}
