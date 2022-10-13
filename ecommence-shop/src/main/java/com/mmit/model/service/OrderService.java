package com.mmit.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entities.Orders;
import com.mmit.model.repo.OrderRepo;

@Service
public class OrderService {
	@Autowired
	private OrderRepo repo;

	public Orders save(Orders new_order) {
		return repo.save(new_order);
	}

	public List<Orders> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Orders findById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(new Orders());
	}
}
