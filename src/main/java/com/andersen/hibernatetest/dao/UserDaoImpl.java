package com.andersen.hibernatetest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import com.andersen.hibernatetest.entity.User;

public class UserDaoImpl {
	
	private SessionFactory factory;
	
	public UserDaoImpl(){
		this.factory = getSessionFactory();
	}
	
	private SessionFactory getSessionFactory(){
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
		return factory;		
	}
	
	
	public int create(User user){		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		return user.getId();
	}
	
	public void update(User user){						
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		User updatedUser = session.get(User.class, user.getId());
		updatedUser.setFirstname(user.getFirstname());
		updatedUser.setLastname(user.getLastname());
		updatedUser.setEmail(user.getEmail());
		
		session.update(updatedUser);
		session.getTransaction().commit();
	}
	
	public void delete(User user){						
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		User deletingUser = session.get(User.class, user.getId());
		session.delete(deletingUser);
		session.getTransaction().commit();
	}
	
	public User getById(int id){
		Session session = factory.getCurrentSession();
		User selectedUser = new User();
		
		session.beginTransaction();
		selectedUser = session.get(User.class, id);
		session.getTransaction().commit();
		
		return selectedUser;
	}
	
	public List<User> getAll(){
		Session session = factory.getCurrentSession();
		List<User> users = new ArrayList<User>();
		
		session.beginTransaction();
		users = session.createQuery("from " + User.class.getName()).list();
		session.getTransaction().commit();
		return users;
	}

}
