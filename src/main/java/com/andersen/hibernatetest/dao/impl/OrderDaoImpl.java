package com.andersen.hibernatetest.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.hibernatetest.dao.OrderDao;
import com.andersen.hibernatetest.model.Order;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao{
			
	@PersistenceContext
	private EntityManager entityManager;	
		
	public int create(Order order){		
		entityManager.persist(order);		
		return order.getId();
	}	
	
	public void update(Order order){
		entityManager.merge(order);	
	}	
	
	public void delete(Order order){
		Order deletingorder = entityManager.find(Order.class, order.getId());
		entityManager.remove(deletingorder);		
	}
		
	public Order getById(int id){
		return entityManager.find(Order.class, id);
	}	
	
	public List<Order> getAll(){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Order> cq = builder.createQuery(Order.class);
	    Root<Order> root = cq.from(Order.class);
	    cq.select(root);
	    return entityManager.createQuery(cq).getResultList();
	}

}
