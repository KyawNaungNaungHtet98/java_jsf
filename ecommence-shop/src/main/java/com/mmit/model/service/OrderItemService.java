package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entities.OrderItem;
import com.mmit.model.repo.OrderItemRepo;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepo repo;

	public List<OrderItem> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
}
