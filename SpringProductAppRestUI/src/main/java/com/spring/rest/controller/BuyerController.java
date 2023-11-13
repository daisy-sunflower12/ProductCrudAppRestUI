package com.spring.rest.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rest.bean.CookieUtil;
import com.spring.rest.bean.PurchaseBean;
import com.spring.rest.bean.ResponseMsg;
import com.spring.rest.dao.ProductDao;
import com.spring.rest.dao.PurchaseDao;
import com.spring.rest.entity.Product;
import com.spring.rest.entity.ProductList;
import com.spring.rest.entity.Purchase;
import com.spring.rest.entity.PurchaseList;
import com.spring.rest.entity.User;

@RestController
public class BuyerController {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CookieUtil cookieUtil;

	@Autowired
	private PurchaseDao purchaseDao;

	@RequestMapping(value = "/buyer")
	public ModelAndView buyerPage(HttpServletRequest request, Model m) {

		User userFromCookie = cookieUtil.getUserFromCookie(request);
		Integer bId = userFromCookie.getId();
		double balance = userFromCookie.getBalance();
		System.out.println("B ID: " + bId);
		System.out.println("Buyer Bal: " + balance);
		m.addAttribute("bId", bId);
		m.addAttribute("buyerBal", userFromCookie.getBalance());
		ModelAndView mav = new ModelAndView("buyer");
		return mav;
	}

	@RequestMapping(value = "/allProducts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList showProducts(HttpServletRequest request, Model m) {
		
		return new ProductList(productDao.showAllProducts());
	}

	@RequestMapping(value = "/orderNow/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMsg> addOrder(@RequestBody PurchaseBean purchase, @PathVariable("id") Integer pId,
			HttpServletRequest request, Model m) {

		ResponseMsg msg = new ResponseMsg();
		msg.setSuccess("Purchase successful");
		msg.setError("Purchase unsuccessful");
		System.out.println("Purchase: " + purchase);

		// buyer Id
		User userFromCookie = cookieUtil.getUserFromCookie(request);
		System.out.println("Buyer id: " + userFromCookie.getId());

		System.out.println("Product id: " + pId);
		Product prod = productDao.getProductById(pId);

		// seller Id
		prod.getUser().getId();
		System.out.println("Seller Id: " + prod.getUser().getId());

		User user = new User();
		user.setId(prod.getUser().getId());

		// seller balance
		user.setBalance(user.getBalance() + (purchase.getTotalUnit() * prod.getProdSellPrice()));
		System.out.println("Seller Balance: " + user.getBalance());
		// purchase Date
		purchase.setPurchaseDt(purchase.getPurchaseDt().toString());
		System.out.println("Date: " + purchase.getPurchaseDt());
		System.out.println(new Date());

		prod.setStockUnit(prod.getStockUnit() - purchase.getTotalUnit());
		System.out.println("Total Qty: " + prod.getStockUnit());
		// buyer balance
		userFromCookie.setBalance(userFromCookie.getBalance() - prod.getProdCostPrice());
		System.out.println("Buyer bal: " + user.getBalance());
		purchase.setTotalUnit(purchase.getTotalUnit());
		System.out.println("Total unit: " + purchase.getTotalUnit());

		System.out.println("Product cost price: " + prod.getProdSellPrice());
		// purchase total price
		purchase.setTotalCostPrice(prod.getProdSellPrice() * purchase.getTotalUnit());
		System.out.println("Total Price: " + purchase.getTotalCostPrice());

		purchase.setBuyer(userFromCookie.getId());
		System.out.println("Buyer id: " + purchase.getBuyer());

		purchase.setSeller(user.getId());
		System.out.println("Seller id: " + purchase.getSeller());
		purchase.setProduct(prod);
		System.out.println("Purchase: " + purchase);

		System.out.println("Update balance: " + (userFromCookie.getBalance() - prod.getProdCostPrice()));
		if (userFromCookie.getBalance() - prod.getProdCostPrice() >= 0) {
			purchaseDao.insertOrder(purchase);
			purchaseDao.updateProduct(prod);
			purchaseDao.updateBuyerBal(userFromCookie);
			System.out.println("Buyer bal after purchase: " + userFromCookie.getBalance());
			purchaseDao.updateSellBalance(user);
			System.out.println("Seller bal after purchase: " + user.getBalance());
			return new ResponseEntity<ResponseMsg>(msg, HttpStatus.OK);

		} else {
			return new ResponseEntity<ResponseMsg>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/allPurchases")
	public ModelAndView sellerPage() {
		ModelAndView mav = new ModelAndView("purchases");
		return mav;
	}

	@RequestMapping("/viewpurchase")
	public PurchaseList showPurchase(HttpServletRequest request, Model m) {

		User userFromCookie = cookieUtil.getUserFromCookie(request);
		userFromCookie.getId();
		System.out.println("User id: " + userFromCookie.getId());
		System.out.println(purchaseDao.getPurchaseByBuyerId(userFromCookie.getId()));
		return new PurchaseList(purchaseDao.getPurchaseByBuyerId(userFromCookie.getId()));
	}

	@DeleteMapping("/cancelOrder/{id}")
	public ResponseEntity<ResponseMsg> cancelOrder(@PathVariable("id") Integer id, HttpServletRequest request) {

		ResponseMsg msg = new ResponseMsg();
		msg.setSuccess("Purchase successful");
		msg.setError("Purchase unsuccessful");
		try {
			System.out.println("Selected id: " + id);
			Purchase purId = purchaseDao.getPurchaseById(id);
			int prodId = purId.getProduct().getId();
			System.out.println("Product id: " + prodId);

			// PRODUCT
			Product product = productDao.getProductById(prodId);
			product.setStockUnit(product.getStockUnit() + purId.getTotalUnit());
			System.out.println("Stock after cancel order: " + product.getStockUnit());

			// Seller
			product.getUser().getId();
			System.out.println("Seller Id: " + product.getUser().getId());

			User u = new User();
			u.setId(product.getUser().getId());
			u.setBalance(u.getBalance() - ((purId.getTotalUnit() * product.getProdSellPrice())));

			// BUYER Bal
			User user = cookieUtil.getUserFromCookie(request);
			System.out.println("Buyer Id: " + user.getId());
			user.setBalance(user.getBalance() + (purId.getTotalUnit() * product.getProdSellPrice()));

			purId.setProduct(product);
			if (purId != null) {
				purchaseDao.cancelOrder(id);
				purchaseDao.updateProduct(product);
				purchaseDao.updateSellBalance(u);
				purchaseDao.updateBuyerBal(user);
				return new ResponseEntity<ResponseMsg>(msg, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseMsg>(msg, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@RequestMapping("/buyerSummary")
	public ModelAndView summary() {		
		ModelAndView mav = new ModelAndView("buy_summary");
		return mav;
	}
	
	//@RequestMapping("/buyerSummary")
}
