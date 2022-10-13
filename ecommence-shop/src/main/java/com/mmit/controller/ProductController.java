package com.mmit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	@GetMapping("/products/detail")
	public String detailPage() {
		return "product-detail";
		
	}
}
