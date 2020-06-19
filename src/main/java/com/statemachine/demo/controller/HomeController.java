package com.statemachine.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.statemachine.demo.dao.impl.ProductServiceDAOImpl;
import com.statemachine.demo.model.Product;
import com.statemachine.demo.service.ProductService;

@RestController
public class HomeController {
	
	@Autowired
	ProductServiceDAOImpl produtServiceDao;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/")
	public void insetData() {
		Product product = new Product("Paddy");
		produtServiceDao.insertData(product);
	}
	
	@RequestMapping("/get")
	public List<Product> getData() {
		return produtServiceDao.getData();
	}
}
