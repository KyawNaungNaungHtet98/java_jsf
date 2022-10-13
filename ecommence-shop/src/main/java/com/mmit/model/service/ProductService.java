package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entities.Product;
import com.mmit.model.repo.ProductRepo;
@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;

	public List<Product> findAll() {
		
		return repo.findAll();
	}

	public Product findById(long productId) {
		
		return repo.findById(productId).orElse(new Product());
	}

	public Product save(Product prod) {
		// TODO Auto-generated method stub
		return repo.save(prod);
	}

	public void deleteById(long id) {
		repo.deleteById(id);
		
	}
	
	
}
