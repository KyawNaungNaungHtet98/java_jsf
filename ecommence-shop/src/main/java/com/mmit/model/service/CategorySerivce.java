package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entities.Category;
import com.mmit.model.repo.CategoryRepo;

@Service
public class CategorySerivce {
	@Autowired
	private CategoryRepo repo;

	public List<Category> findAll() {
		return repo.findAll();
	}

	public Category save(Category category) {
		// TODO Auto-generated method stub
		return repo.save(category);
	}
}
