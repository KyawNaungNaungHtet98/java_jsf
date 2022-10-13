package com.mmit.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.model.entities.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long>{

}
