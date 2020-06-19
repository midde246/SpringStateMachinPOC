package com.statemachine.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.statemachine.demo.dao.ProductRepository;
import com.statemachine.demo.dao.ProductServiceDAO;
import com.statemachine.demo.model.Product;

@Component
public class ProductServiceDAOImpl implements ProductServiceDAO{
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void insertData(Product product){
		productRepository.save(product);
	}

	@Override
	public List<Product> getData() {
		return productRepository.findAll();
	}
}
