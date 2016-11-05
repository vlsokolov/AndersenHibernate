package com.andersen.hibernatetest.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;
	
	@Column(name="first_name")
	private String firstname;
	
	@Column(name="last_name")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Order> orders = new HashSet<Order>();
		
	public User(){		
	}
	
	public User(String firstname, String lastname){
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public User(String firstname, String lastname, String email){
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	@Override
	public String toString() {
		if(email != null){
			return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + "]";
		} else {
			return "User [firstname=" + firstname + ", lastname=" + lastname + "]";
		}
	}
	
	

}
