package com.andersen.hibernatetest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.andersen.hibernatetest.dao.impl.UserDaoImpl;
import com.andersen.hibernatetest.model.User;

public class UserDaoTest {
	
	UserDaoImpl userDao;
	User user;
	int id;
	
	@Before
	public void setUp(){
	userDao = new UserDaoImpl();
	user = new User("testfirstname", "testlastname", "testemail");	
	}
	
	@After
	public void tearDown(){
		List<User> users = userDao.getAll();
		for(User user: users){
			if(user.getEmail().equals("testemail")){
				userDao.delete(user);
			}
		}
	}
	
			
	@Test
	public void insertTest(){
		id = userDao.create(user);
		User retrievedUser = userDao.getById(id);
		assertEquals(user.getFirstname(), retrievedUser.getFirstname());
		assertEquals(user.getLastname(), retrievedUser.getLastname());
		assertEquals(user.getEmail(), retrievedUser.getEmail());
	}
    
	@Test
	public void updateTest(){
		id = userDao.create(user);
		String updatedFirstName = "updatedFirstTestName";
		user.setFirstname(updatedFirstName);
		
		userDao.update(user);
		User retrievedUser = userDao.getById(id);
		assertEquals(updatedFirstName, retrievedUser.getFirstname());
	}
	
	@Test
	public void deleteTest(){
		id = userDao.create(user);				
		userDao.delete(user);
		User retrievedUser = userDao.getById(id);
		assertNull(retrievedUser);
	}
	
	@Test
	public void getAllTest(){
		List<User> users = new ArrayList<User>();
		assertEquals(0, users.size());
		users = userDao.getAll();
		assertTrue(users.size() > 0);
	}
	
}
