package com.mmit.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.model.entities.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long>{

}
