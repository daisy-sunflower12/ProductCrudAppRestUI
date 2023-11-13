package com.spring.rest.dao;

import java.util.List;

import com.spring.rest.bean.PurchaseBean;
import com.spring.rest.entity.Product;
import com.spring.rest.entity.Purchase;
import com.spring.rest.entity.User;

public interface PurchaseDao {

	public PurchaseBean insertOrder(PurchaseBean purchase);

	public Purchase getPurchaseById(int id);

	public List<Purchase> getAllPurchases();

	public void cancelOrder(int id);

	public List<Purchase> getAllCartProducts(List<Purchase> purchases);
	
	public Product updateProduct(Product product);
	
	public User updateBuyerBal(User user);
	
	public User updateSellBalance(User user);
	
	public List<Purchase> getPurchaseByBuyerId(int id);

	public List<Purchase> getPurchaseBySellerId(int id);
}
