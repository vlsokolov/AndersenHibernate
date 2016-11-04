package com.andersen.hibernatetest.dao;

import java.util.List;

public interface GenericDao<T> {

	public int create(T o);
	public void update(T t);
	public void delete(T r);
	public T getById(int id);
	public List<T> getAll();
}
