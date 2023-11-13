package com.spring.rest.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.bean.ProductBean;
import com.spring.rest.entity.Product;

@SuppressWarnings("deprecation")
@Repository
public class ProductDaoImpl implements ProductDao {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Product> getAllProducts(int id) {
		List<Product> list = null;

		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			SQLQuery query = sess.createSQLQuery("Select * from products where isDeleted=1 and seller_id=?1");
			query.addEntity(Product.class);
			query.setParameter(1, id);
			list = query.list();
			for (Product prod : list) {
				System.out.println(prod);

			}
			tx.commit();
			sess.close();
			System.out.println("Product list: " + list.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Product List exception " + e);
		}
		return list;
	}

	@Override
	public ProductBean createProduct(ProductBean product) {
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		product.setIsDeleted((byte) 1);
		sess.save(product.convertProduct());
		tx.commit();
		sess.close();
		return product;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Product updateProduct(Product product) {
		Product prod = new Product();
		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			String sql = "update products set prodCostPrice=?, prodDesc=?, prodName=?, "
					+ "prodSellPrice=?, stockUnit=?, seller_id=? where prod_id=?";
			Query query = sess.createSQLQuery(sql);
			query.setParameter(1, product.getProdCostPrice());
			query.setParameter(2, product.getProdDesc());
			query.setParameter(3, product.getProdName());
			query.setParameter(4, product.getProdSellPrice());
			query.setParameter(5, product.getStockUnit());
			query.setParameter(6, product.getUser().getId());
			query.setParameter(7, product.getId());
			query.executeUpdate();
			tx.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update Product exception: " + e);
		}
		return prod;

	}

	@Override
	public void deleteProduct(int id) {

	}

	@Override
	public Product getProductById(int id) {
		Product bean = new Product();

		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			bean = sess.get(Product.class, id);
			tx.commit();
			sess.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e);
		}

		return bean;
	}

	@Override
	public void removeProduct(int id) {
		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = sess.createSQLQuery("update products set isDeleted=0 where prod_id=?1");
			query.setParameter(1, id);
			query.executeUpdate();
			tx.commit();
			sess.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Eception " + e);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Product> showAllProducts() {

		List<Product> list = null;

		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			SQLQuery query = sess.createSQLQuery("Select * from products where isDeleted=1 and stockUnit > 0");
			query.addEntity(Product.class);
			list = query.list();
			for (Product prod : list) {
				System.out.println(prod);
			}
			tx.commit();
			sess.close();
			System.out.println("Product list: " + list.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Product List exception " + e);
		}
		return list;
	}

}
