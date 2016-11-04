package com.andersen.hibernatetest.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.andersen.hibernatetest.service.OrderService;

public class App {
		
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring.xml");
		
		OrderService orderSevice = (OrderService) ctx.getBean(OrderService.class);
		orderSevice.getMostValuableUser();
	}
}
