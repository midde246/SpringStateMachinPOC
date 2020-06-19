package com.statemachine.demo.dao;

import java.util.List;

import com.statemachine.demo.model.Product;

public interface ProductServiceDAO {
	public void insertData(Product product);

	public List<Product> getData();
}
