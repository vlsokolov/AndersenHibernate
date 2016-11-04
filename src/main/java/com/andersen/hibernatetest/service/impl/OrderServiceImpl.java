package com.andersen.hibernatetest.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.hibernatetest.dao.OrderDao;
import com.andersen.hibernatetest.dao.UserDao;
import com.andersen.hibernatetest.model.Order;
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
			
		List<Order> orders = orderDao.getAll();	
		int foundUserId = findUserWithMaxNumberOfOrders(orders);
		System.out.println(userDao.getById(foundUserId));
	}

	private static int findUserWithMaxNumberOfOrders(List<Order> orders){
		int max = 0;
		int temp = 0;
		int userId = 0;
		
		Set<Integer> usersId = new HashSet<Integer>();
		for(Order order: orders){
			usersId.add(order.getUser().getId());		
		}		
		
		for(Integer index: usersId){			
			temp = 0;
			for(Order order: orders){
				if(order.getUser().getId() == index){
					temp++;
				}				
			}	
			if(temp > max){
				max = temp;	
				userId = index;
			}										
		}		
		return userId;
	}

}
