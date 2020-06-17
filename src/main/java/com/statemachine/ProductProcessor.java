package com.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.statemachine.demo.dao.ProductRepository;
import com.statemachine.demo.model.Product;

@Component
public class ProductProcessor {

	@Autowired
	private ProductRepository productRepository;
	
	public Product newBacklog() {
		Product product = new Product();
		
		return product;
	}
}
