package com.mmit.controller.request;

import java.util.List;

public class OrderRequestData {
	private OrderReceiverData reciver;
	private List<OrderProductData> orderItems;
	public OrderReceiverData getReciver() {
		return reciver;
	}

	public void setReciver(OrderReceiverData reciver) {
		this.reciver = reciver;
	}

	
	public List<OrderProductData> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderProductData> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "OrderRequestData [reciver=" + reciver + "]";
	}
	
}
