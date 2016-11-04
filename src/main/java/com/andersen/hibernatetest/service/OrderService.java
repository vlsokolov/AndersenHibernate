package com.andersen.hibernatetest.service;

import java.util.List;

import com.andersen.hibernatetest.model.Order;

public interface OrderService {
	
	public int create(Order order);
	public void update(Order order);
	public void delete(Order order);
	public Order getById(int id);
	public List<Order> getAll();
	public void getMostValuableUser();
}
