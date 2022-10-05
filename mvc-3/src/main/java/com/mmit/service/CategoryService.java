package com.mmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.repo.CategoryRepo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepo repo;

	public Object findAll() {
		return repo.findAll();	}
}
