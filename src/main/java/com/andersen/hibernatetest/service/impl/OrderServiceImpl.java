package com.andersen.hibernatetest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.hibernatetest.dao.OrderDao;
import com.andersen.hibernatetest.dao.UserDao;
import com.andersen.hibernatetest.model.Order;
import com.andersen.hibernatetest.model.User;
import com.andersen.hibernatetest.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;

	public int create(Order order) {
		return orderDao.create(order);
	}

	public void update(Order order) {
		orderDao.update(order);
	}

	public void delete(Order order) {
		orderDao.delete(order);
	}

	public Order getById(int id) {
		return orderDao.getById(id);
	}

	public List<Order> getAll() {
		return orderDao.getAll();
	}
	
	public void getMostValuableUser(){		
	
		List<User> users = userDao.getAll();
		
		int max = 0;
		int index = 0;
		
		for(User user: users){
			if(user.getOrders().size() > max){
				max = user.getOrders().size();
				index = users.indexOf(user);
			}
				
		}
		
		System.out.println(users.get(index));
	}
}
