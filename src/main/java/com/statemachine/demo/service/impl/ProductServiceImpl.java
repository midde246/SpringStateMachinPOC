package com.statemachine.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import com.statemachine.demo.dao.ProductRepository;
import com.statemachine.demo.model.Product;
import com.statemachine.demo.service.ProductService;
import com.statemachine.demo.util.Events;
import com.statemachine.demo.util.States;

@Service
public class ProductServiceImpl implements ProductService {

	public static final String PRODUCT_HEADER = "product_id";
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateMachineFactory<States, Events> stateMachineFactory;
	@Autowired
	private ProductStateChangeInterceptor productStateChangeInterceptor;
	
	@Override
	public Product newProduct(Product product) {
		product.setStates(States.BACKLOG);
		return productRepository.save(product);
	}

	@Override
	public StateMachine<States, Events> startFeature(int prodId) {
		StateMachine<States, Events> sm = build(prodId);
		sendEvent(prodId, sm, Events.START_FEATURE);
		return sm;
	}

	@Override
	public StateMachine<States, Events> finishFeature(int prodId) {
		StateMachine<States, Events> sm = build(prodId);
		sendEvent(prodId, sm, Events.FINISH_FEATURE);
		return sm;
	}

	@Override
	public StateMachine<States, Events> qaRejected(int prodId) {
		StateMachine<States, Events> sm = build(prodId);
		sendEvent(prodId, sm, Events.QA_REJECTED);
		return sm;
	}

	@Override
	public StateMachine<States, Events> qaAccepted(int prodId) {
		StateMachine<States, Events> sm = build(prodId);
		sendEvent(prodId, sm, Events.QA_ACCEPTED);
		return sm;
	}

	private void sendEvent(int prodId, StateMachine<States, Events> sm, Events event) {
		Message msg = MessageBuilder.withPayload(event).setHeader(PRODUCT_HEADER, prodId).build();
		sm.sendEvent(msg);
	}
	
	private StateMachine<States, Events> build(int prodId){
		Product product = productRepository.getOne(prodId);
		StateMachine<States, Events> sm = stateMachineFactory.getStateMachine(Integer.toString(product.getProdId()));		
		
		sm.stop();
		
		sm.getStateMachineAccessor()
		  .doWithAllRegions(sma -> {
			  sma.addStateMachineInterceptor(productStateChangeInterceptor);
			 sma.resetStateMachine(new DefaultStateMachineContext<States, Events>(product.getStates(), null, null, null)); //event, eventHeaders, extendedState 
		  });
		
		sm.start();
		
		return sm;	
	}
}
