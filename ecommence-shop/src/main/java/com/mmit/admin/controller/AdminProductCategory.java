package com.mmit.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mmit.FileUploadutil;
import com.mmit.model.entities.Category;
import com.mmit.model.entities.Product;
import com.mmit.model.service.CategorySerivce;
import com.mmit.model.service.ProductService;

@Controller
public class AdminProductCategory {
	@Autowired
	private ProductService prodService;
	@Autowired
	private CategorySerivce cateService;
	
	@ModelAttribute
	public void assignDefualtModel(Model m) {
		m.addAttribute("page","product");
		m.addAttribute("product",new Product());
		
	}
	
	
	@GetMapping("/admin/product")
	public String goHome(Model m) {
		 List<Product> list = prodService.findAll();
		 
		 m.addAttribute("productList",list);
		 
		return "admin/product";
	}

	@GetMapping("/admin/productAdd")
	public String goAdd(Model m) {
		m.addAttribute("categoryList",cateService.findAll());
		return "admin/product-add";
	}
	
	@PostMapping("/admin/productSave")
	
		public String goSave(@ModelAttribute("product") Product prod, @RequestParam("photo_file") MultipartFile file) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
			if((prod.getId() == 0 || prod.getId() != 0 )&& !fileName.equals("")) {
				prod.setPhoto(fileName);
				
			}
			
			//save to database
			var saveProduct = prodService.save(prod);
			//save photo
			if(!"".equals(fileName)) {
				String uploadDir = "uploads/" + saveProduct.getId();// ->>>>> upload-photos/1
				FileUploadutil.savePhoto(uploadDir,fileName,file);
				
			}
			 String redirectUrl = "/product";
			return "redirect:/admin"+ redirectUrl;
	}
	
	@GetMapping("/admin/productEdit/{id}")
	public String editProduct(@PathVariable("id") long productId , Model m) {
		var prod = prodService.findById(productId);
		m.addAttribute("categoryList",cateService.findAll());
		m.addAttribute("product",prod);
		return "admin/product-add";
		
	}
	@GetMapping("/admin/productDelete/{id}")
	public String deleteProduct(@PathVariable("id") long id) {
		prodService.deleteById(id);
		String redirectUrl = "/product";
		return "redirect:/admin"+ redirectUrl;
	}
}

