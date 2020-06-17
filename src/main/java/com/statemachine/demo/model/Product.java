package com.statemachine.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.statemachine.demo.util.States;

@Entity
@Table(name = "Product_Details")
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "Product_Id")
	private int prodId;
	
	@Column(name="Product_Name")
	private String prodName;
	
	@Enumerated(EnumType.STRING)
	private States states;
	
	public Product() {}

	public Product(String prodName) {
		this.prodName = prodName;
	}

	public String getProdName() {
		return prodName;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public int getProdId() {
		return prodId;
	}
	
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public States getStates() {
		return states;
	}

	public void setStates(States states) {
		this.states = states;
	}
	
}
