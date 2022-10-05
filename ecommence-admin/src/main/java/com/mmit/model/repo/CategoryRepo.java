package com.mmit.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.model.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
