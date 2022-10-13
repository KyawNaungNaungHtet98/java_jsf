package com.mmit.controller;

import java.security.Principal;
import java.util.List;

import javax.sound.midi.Receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mmit.controller.request.OrderProductData;
import com.mmit.controller.request.OrderReceiverData;
import com.mmit.controller.request.OrderRequestData;
import com.mmit.model.entities.OrderItem;
import com.mmit.model.entities.OrderStatus;
import com.mmit.model.entities.Orders;
import com.mmit.model.entities.User;
import com.mmit.model.repo.OrderRepo;
import com.mmit.model.service.OrderService;
import com.mmit.model.service.ProductService;
import com.mmit.model.service.UserService;

@Controller
public class CartController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/cart/detail")
	public String home() {
		return "cart";
	}
	@GetMapping("/cart/checkout")
	public String checkoutPage(ModelMap map,Principal principal) {
		User loginUser = userService.profile(principal.getName());
		map.put("name", loginUser.getName());
		map.put("phone", loginUser.getPhone());
		map.put("email", loginUser.getEmail());
		map.put("address", loginUser.getAddress());
		return "checkout";
	}
	
	@PostMapping("/cart/place-order")
	public @ResponseBody String makeOrder(@RequestBody OrderRequestData obj,Principal principal) {
	
		try {
			OrderReceiverData receiver = obj.getReciver();
			List<OrderProductData> itemList = obj.getOrderItems();
			
			
			//create new order
			Orders new_order = new Orders();
			new_order.setStatus(OrderStatus.pending);
			new_order.setShippingAddress(receiver.getAddress());
			new_order.setShippingEmail(receiver.getEmail());
			new_order.setShippingName(receiver.getName());
			new_order.setShippingPhone(receiver.getPhone());
			//User customer = userService.profile(principal.getName());
			//new_order.setCustomer(customer);
			
			//Add Order Items
			for(var item:itemList) {
				var product = productService.findById(item.getProductId());
				OrderItem order_item = new OrderItem();
				order_item.setProduct(product);
				order_item.setQuantity(item.getQty());
				
				new_order.addOrderItem(order_item);
			}
			
			//Save Order to database
			Orders savedOrder = orderService.save(new_order);
			return savedOrder.getId() + "";
			
		} catch (Exception e) {
			return "";
		}
		
	}
}
