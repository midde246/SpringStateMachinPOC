package com.statemachine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import com.statemachine.demo.dao.ProductRepository;
import com.statemachine.demo.model.Product;
import com.statemachine.demo.util.Events;
import com.statemachine.demo.util.States;
import com.statemachine.service.ProductService;

@SpringBootTest
public class ProductServiceImplTest {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
//	@Transactional
	@Test
	void newProductTest() {
		Product productReq = new Product("Tea");
		Product product = productService.newProduct(productReq);
		System.out.println("Initial State :: "+product.getStates());
		
		StateMachine<States, Events> sm = productService.startFeature(product.getProdId());
		Product startFeature = productRepository.getOne(product.getProdId());
		
		System.out.println("Current State-------->>> "+startFeature.getStates().toString());
//		System.out.println("Current State-------->>> "+sm.get);
		assertTrue(true);
	}
}
