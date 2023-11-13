package com.spring.rest.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.spring.rest.entity.Product;
import com.spring.rest.entity.Purchase;
import com.spring.rest.entity.User;

public class PurchaseBean {

	private int purchaseId;
	private String purchaseDt;
	private int totalUnit;
	private double totalCostPrice;
	private int buyer;
	private int seller;

	private Product product;

	public PurchaseBean() {
		super();
	}

	public PurchaseBean(int purchaseId, String purchaseDt, int totalUnit, double totalCostPrice, Product product,
			int buyer, int seller) {
		super();
		this.purchaseId = purchaseId;
		this.purchaseDt = purchaseDt;
		this.totalUnit = totalUnit;
		this.totalCostPrice = totalCostPrice;
		this.product = product;
		this.buyer = buyer;
		this.seller = seller;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseDt() {
		return purchaseDt;
	}

	public void setPurchaseDt(String purchaseDt) {
		this.purchaseDt = purchaseDt;
	}

	public int getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(int totalUnit) {
		this.totalUnit = totalUnit;
	}

	public double getTotalCostPrice() {
		return totalCostPrice;
	}

	public void setTotalCostPrice(double totalCostPrice) {
		this.totalCostPrice = totalCostPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getBuyer() {
		return buyer;
	}

	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}

	public int getSeller() {
		return seller;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}

	public Purchase convertToPurchase() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Purchase pur = new Purchase();
		pur.setPurchaseId(purchaseId);
		try {
			pur.setPurchaseDt(sdf.parse(purchaseDt));
			System.out.println("Date: "+pur.getPurchaseDt());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		pur.setProduct(product);
		pur.setTotalCostPrice(totalCostPrice);
		pur.setTotalUnit(totalUnit);

		User by = new User();
		by.setId(buyer);
		pur.setBuyer(by);

		User slr = new User();
		slr.setId(seller);
		pur.setSeller(slr);
		return pur;

	}

	@Override
	public String toString() {
		return "PurchaseBean [purchaseId=" + purchaseId + ", purchaseDt=" + purchaseDt + ", totalUnit=" + totalUnit
				+ ", totalCostPrice=" + totalCostPrice + ", product=" + product + ", buyer=" + buyer + ", seller="
				+ seller + "]";
	}

}
