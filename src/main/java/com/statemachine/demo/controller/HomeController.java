package com.statemachine.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.statemachine.demo.dao.ProductServiceDAO;
import com.statemachine.demo.model.Product;
import com.statemachine.demo.util.States;
import com.statemachine.service.ProductService;

@RestController
public class HomeController {
	
	@Autowired
	ProductServiceDAO produtServiceDao;
	
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
