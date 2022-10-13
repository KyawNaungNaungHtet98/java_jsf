package com.mmit.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.model.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
