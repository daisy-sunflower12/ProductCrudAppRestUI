package com.spring.rest.entity;

import java.util.List;

public class ProductList {

	public List<Product> products;

	public ProductList() {
		super();
	}

	public ProductList(List<Product> products) {
		super();
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
