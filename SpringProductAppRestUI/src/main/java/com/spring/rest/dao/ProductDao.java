package com.spring.rest.dao;

import java.util.List;

import com.spring.rest.bean.ProductBean;
import com.spring.rest.entity.Product;

public interface ProductDao {

	public List<Product> getAllProducts(int id);
	
	public List<Product> showAllProducts();

	public ProductBean createProduct(ProductBean product);
	
	public Product updateProduct(Product product);

	public void deleteProduct(int id);

	public Product getProductById(int id);
	
	public void removeProduct(int id);

}
