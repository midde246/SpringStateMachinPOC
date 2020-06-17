package com.statemachine.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.statemachine.demo.model.Product;

@Component
public class ProductServiceDAO {
	
	@Autowired
	ProductRepository productRepository;
	
	public void insertData(Product product){
		productRepository.save(product);
	}

	public List<Product> getData() {
		return productRepository.findAll();
	}
}
