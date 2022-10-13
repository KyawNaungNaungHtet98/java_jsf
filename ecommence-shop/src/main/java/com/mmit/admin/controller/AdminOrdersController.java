package com.mmit.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.mmit.model.entities.OrderStatus;
import com.mmit.model.entities.Orders;
import com.mmit.model.entities.Product;
import com.mmit.model.entities.User;
import com.mmit.model.service.OrderItemService;
import com.mmit.model.service.OrderService;
import com.mmit.model.service.UserService;


@Controller
public class AdminOrdersController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderitemService;
	@GetMapping("/admin/orders")
	private String goHome(Model m) {
		List<Orders> list = orderService.findAll();
		 
		 m.addAttribute("orders",list);
		return "admin/orders";
	}
	@ModelAttribute
	public void assignDefualtModel(Model m) {
		
		m.addAttribute("orders",new Orders());
		
	}
	
	@GetMapping("/admin/orderItem")
	private String goHomeOrderItem(Model m) {
		m.addAttribute("orderList",orderitemService.findAll());
		
		return "admin/order-list";
	}
	@GetMapping("/admin/received/{id}")
	private String changeStatusReceived(@PathVariable("id") long id,Model m) {
		var orders = orderService.findById(id);
		orders.setStatus(OrderStatus.received);
		orderService.save(orders);
		m.addAttribute("orderList",orderitemService.findAll());
		String redirectUrl = "/orders";
		return "redirect:/admin"+ redirectUrl;
		
		
		
	}
	@GetMapping("/admin/deliever/{id}")
	private String changeStatusDeliever(@PathVariable("id") long id,Model m) {
		var orders = orderService.findById(id);
		orders.setStatus(OrderStatus.delievered);
		orderService.save(orders);
		m.addAttribute("orderList",orderitemService.findAll());
		String redirectUrl = "/orders";
		return "redirect:/admin"+ redirectUrl;
		
		
		
	}
	@GetMapping("/admin/cancel/{id}")
	private String changeStatusCancel(@PathVariable("id") long id,Model m) {
		var orders = orderService.findById(id);
		orders.setStatus(OrderStatus.cancel);
		orderService.save(orders);
		m.addAttribute("orderList",orderitemService.findAll());
		String redirectUrl = "/orders";
		return "redirect:/admin"+ redirectUrl;
		
		
		
	}
	
}
