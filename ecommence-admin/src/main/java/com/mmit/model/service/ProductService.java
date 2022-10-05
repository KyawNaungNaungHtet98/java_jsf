package com.mmit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;
}
