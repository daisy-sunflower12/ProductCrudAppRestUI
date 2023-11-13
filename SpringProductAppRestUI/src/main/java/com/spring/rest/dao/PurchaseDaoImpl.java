package com.spring.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.rest.bean.PurchaseBean;
import com.spring.rest.entity.Product;
import com.spring.rest.entity.Purchase;
import com.spring.rest.entity.User;

@Repository
public class PurchaseDaoImpl implements PurchaseDao {

	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public PurchaseBean insertOrder(PurchaseBean purchase) {
		
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(purchase.convertToPurchase());
		tx.commit();
		sess.close();
		return purchase;
	}

	@Override
	public Purchase getPurchaseById(int id) {
		Purchase pr = new Purchase();
		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			pr = sess.get(Purchase.class, id);
			tx.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Product Id Excep: " + e);
		}
		return pr;
	}

	// Currently unused
	@Override
	public List<Purchase> getAllPurchases() {
		return null;
	}

	@Override
	public void cancelOrder(int id) {
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query q = sess.createQuery("delete from Purchase where purchaseId=?1");
		q.setParameter(1, id);
		q.executeUpdate();
		tx.commit();
		sess.close();

	}

	@Override
	public List<Purchase> getAllCartProducts(List<Purchase> purchases) {
		List<Purchase> list = new ArrayList<>();

		try {
			Session sess = factory.openSession();
			if (purchases.size() > 0) {
				for (Purchase c : purchases) {
					System.out.println(c.getProduct().getId());
					@SuppressWarnings("rawtypes")
					Query query = sess.createQuery("from Purchase where product.id=?1");
					query.setParameter(1, c.getProduct().getId());
					list = query.list();
				}
			}
			sess.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Product updateProduct(Product product) {
		Product prod = new Product();

		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			Query query = sess.createQuery("update Product set stockUnit=?1 where id=?2");
			query.setParameter(1, product.getStockUnit());
			query.setParameter(2, product.getId());
			query.executeUpdate();
			tx.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return prod;
	}

	@Override
	public User updateBuyerBal(User user) {
		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = sess.createQuery("update User set balance=?1 where id=?2");
			query.setParameter(1, user.getBalance());
			query.setParameter(2, user.getId());
			query.executeUpdate();

			tx.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return user;
	}

	@Override
	public User updateSellBalance(User user) {
		
		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = sess.createQuery("update User set balance=?1 where id=?2");
			query.setParameter(1, user.getBalance());
			query.setParameter(2, user.getId());
			query.executeUpdate();
			tx.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return user;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Purchase> getPurchaseByBuyerId(int id) {
		List<Purchase> list = new ArrayList<>();

		try {
			Session sess = factory.openSession();

			Query q = sess.createQuery("from Purchase where buyer.id=?1");
			q.setParameter(1, id);
			list = q.list();
			System.out.println("User Id in dao " + id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return list;
	}

	@Override
	public List<Purchase> getPurchaseBySellerId(int id) {
		List<Purchase> list = new ArrayList<>();

		try {
			Session sess = factory.openSession();

			Query q = sess.createQuery("from Purchase where seller.id=?1");
			q.setParameter(1, id);
			list = q.list();
			System.out.println("User Id in dao " + id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}
		return list;
	}

}
