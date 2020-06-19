package com.statemachine.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.statemachine.demo.dao.ProductRepository;
import com.statemachine.demo.model.Product;
import com.statemachine.demo.util.Events;
import com.statemachine.demo.util.States;

@Component
public class ProductStateChangeInterceptor extends StateMachineInterceptorAdapter<States, Events>{
	
	@Autowired
	private ProductRepository productrepository;
	
	@Override
	public void preStateChange(State<States, Events> state, Message<Events> message,
			Transition<States, Events> transition, StateMachine<States, Events> stateMachine) {
		 Optional.ofNullable(message).ifPresent(msg -> {
			 Optional.ofNullable(Integer.class.cast(msg.getHeaders().getOrDefault(ProductServiceImpl.PRODUCT_HEADER, -1))).ifPresent(prodId -> {
				 Product product = productrepository.getOne(prodId);
				 product.setStates(state.getId());
				 productrepository.save(product);
			 });
		 });
		 
	}
}
