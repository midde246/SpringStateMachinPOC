package com.statemachine.demo.service;

import org.springframework.statemachine.StateMachine;

import com.statemachine.demo.model.Product;
import com.statemachine.demo.util.Events;
import com.statemachine.demo.util.States;

public interface ProductService {

	Product newProduct(Product product);
	
	StateMachine<States, Events> startFeature(int prodId);
	
	StateMachine<States, Events> finishFeature(int prodId);
	
	StateMachine<States, Events> qaRejected(int prodId);
	
	StateMachine<States, Events> qaAccepted(int prodId);
	
}
