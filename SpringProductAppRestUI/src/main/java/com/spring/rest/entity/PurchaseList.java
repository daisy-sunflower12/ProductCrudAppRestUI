package com.spring.rest.entity;

import java.util.List;

public class PurchaseList {

	public List<Purchase> purchases;

	public PurchaseList() {
		super();
	}

	public PurchaseList(List<Purchase> purchases) {
		super();
		this.purchases = purchases;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
