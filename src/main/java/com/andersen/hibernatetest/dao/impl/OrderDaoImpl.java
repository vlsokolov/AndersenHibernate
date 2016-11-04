package com.andersen.hibernatetest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.andersen.hibernatetest.model.Order;
import com.andersen.hibernatetest.model.User;

public class OrderDaoImpl extends AbstractDao<Order>{
		
	private User user;
		
	public OrderDaoImpl(User user){
		this.user = user;
	}	
	
	@Override
	public int create(Order order){
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		order.setUser(user);
		session.save(order);
		session.getTransaction().commit();
		
		return order.getId();
	}
	
	@Override
	public void update(Order order){
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		Order updatedOrder = session.get(Order.class, order.getId());
		updatedOrder.setCount(order.getCount());
		updatedOrder.setUser(user);
		
		session.update(updatedOrder);
		session.getTransaction().commit();		
	}
	
	@Override
	public void delete(Order order){
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		session.delete(order);
		session.getTransaction().commit();		
	}
	
	@Override
	public Order getById(int id){
		Session session = factory.getCurrentSession();
		Order selectedOrder = new Order();
		
		session.beginTransaction();
		selectedOrder = session.get(Order.class, id);
		session.getTransaction().commit();
		
		return selectedOrder;
	}
	
	@Override
	public List<Order> getAll(){
		List<Order> orders = new ArrayList<Order>();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		orders =  session.createQuery("from " + Order.class.getName()).list();
		session.getTransaction().commit();
		
		return orders;
	}

}
