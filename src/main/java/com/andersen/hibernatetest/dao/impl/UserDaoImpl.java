package com.andersen.hibernatetest.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.hibernatetest.dao.UserDao;
import com.andersen.hibernatetest.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
			
	@PersistenceContext
	private EntityManager entityManager;
	
	public int create(User user){		
		entityManager.persist(user);		
		return user.getId();
	}
	
	public void update(User user){						
		entityManager.merge(user);
	}
	
	public void delete(User user){			
		User deletinguser = entityManager.find(User.class, user.getId());
		entityManager.remove(deletinguser);
	}
	
	public User getById(int id){
		return entityManager.find(User.class, id);		
	}
	
	public List<User> getAll(){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<User> cq = builder.createQuery(User.class);
	    Root<User> root = cq.from(User.class);
	    cq.select(root);
	    return entityManager.createQuery(cq).getResultList();
	}

}
