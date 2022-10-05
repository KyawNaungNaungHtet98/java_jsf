package com.mmit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductCategory {

	@GetMapping("/product")
	public String goProductTable() {
		return "product";
	}
}
