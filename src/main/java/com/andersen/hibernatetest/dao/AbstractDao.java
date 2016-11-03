package com.andersen.hibernatetest.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class AbstractDao<T> {
	
	protected static final SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.buildSessionFactory();;	
	
	abstract public int create(T o);
	
	abstract public void update(T t);
	
	abstract public void delete(T r);
	
	abstract public T getById(int id);
	
	abstract public List<T> getAll();
}
