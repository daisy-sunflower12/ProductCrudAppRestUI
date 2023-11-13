package com.spring.rest.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_rest")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int purchaseId;
	private Date purchaseDt;
	private int totalUnit;
	private double totalCostPrice;

	@ManyToOne
	@JoinColumn(name = "buyer_id", referencedColumnName = "u_id")
	private User buyer;

	@ManyToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "u_id")
	private User seller;

	@ManyToOne
	@JoinColumn(name = "pr_id")
	private Product product;

	public Purchase() {
		super();
	}

	public Purchase(int purchaseId, Date purchaseDt, int totalUnit, double totalCostPrice, User buyer, User seller,
			Product product) {
		super();
		this.purchaseId = purchaseId;
		this.purchaseDt = purchaseDt;
		this.totalUnit = totalUnit;
		this.totalCostPrice = totalCostPrice;
		this.buyer = buyer;
		this.seller = seller;
		this.product = product;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getPurchaseDt() {
		return purchaseDt;
	}

	public void setPurchaseDt(Date purchaseDt) {
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

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", purchaseDt=" + purchaseDt + ", totalUnit=" + totalUnit
				+ ", totalCostPrice=" + totalCostPrice + ", buyer=" + buyer + ", seller=" + seller + ", product="
				+ product + "]";
	}
	
	

}
