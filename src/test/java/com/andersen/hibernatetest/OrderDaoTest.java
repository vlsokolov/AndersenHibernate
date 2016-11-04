package com.andersen.hibernatetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andersen.hibernatetest.dao.impl.OrderDaoImpl;
import com.andersen.hibernatetest.dao.impl.UserDaoImpl;
import com.andersen.hibernatetest.model.Order;
import com.andersen.hibernatetest.model.User;

public class OrderDaoTest {
	
	OrderDaoImpl orderDao;
	UserDaoImpl userDao;
	Order order;
	User user;
	int id;
	
	@Before
	public void setUp(){
		userDao = new UserDaoImpl();
		user = userDao.getById(1);
		
		orderDao = new OrderDaoImpl(user);
		order = new Order();
		order.setCount(10000);	
		order.setUser(user);
	}
	
	@After
	public void tearDown(){
		List<Order> orders = orderDao.getAll();
		for(Order order: orders){
			if(order.getCount() == 5000 || order.getCount() == 10000){
				orderDao.delete(order);
			}
		}
	}	
			
	@Test
	public void insertTest(){
		id = orderDao.create(order);
		Order retrievedOrder = orderDao.getById(id);
		assertEquals(order.getCount(), retrievedOrder.getCount());
		assertEquals(order.getUser().getId(), retrievedOrder.getUser().getId());	
	}
    
	@Test
	public void updateTest(){
		id = orderDao.create(order);
		int updatedCount = 5000;
				
		order.setCount(updatedCount);
		
		orderDao.update(order);
		Order retrievedOrder = orderDao.getById(id);
		assertEquals(updatedCount, retrievedOrder.getCount());
	}
	
	@Test
	public void deleteTest(){
		id = orderDao.create(order);				
		orderDao.delete(order);
		Order retrievedOrder = orderDao.getById(id);
		assertNull(retrievedOrder);
	}
	
	@Test
	public void getAllTest(){
		List<Order> orders = new ArrayList<Order>();
		assertEquals(0, orders.size());
		orders = orderDao.getAll();
		assertTrue(orders.size() > 0);
	}
	
}



