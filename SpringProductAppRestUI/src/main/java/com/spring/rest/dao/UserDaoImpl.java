package com.spring.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.rest.bean.UserBean;
import com.spring.rest.bean.UserLoginBean;
import com.spring.rest.entity.User;
import com.spring.rest.entity.UserRole;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public User registerUser(User user) {		
		Session sess = factory.getCurrentSession();		
		sess.save(user);
		return user;

	}

	@Override
	public UserBean registerUser(UserBean userBean) {
		Session sess = factory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(userBean.convertToUser());
		tx.commit();
		sess.close();
		return userBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getRoles() {
		List<UserRole> list = new ArrayList<>();
		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			Query<UserRole> q = sess.createQuery("from UserRole");
			list = q.list();
			tx.commit();
			sess.close();
			System.out.println("Roles "+list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User role exception " + e);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public User getUserByEmail(String email) {
		User u = new User();

		try {
			Session sess = factory.openSession();
			Transaction tx = sess.beginTransaction();
			Query query = sess.createQuery("from User where email='" + email + "'");
			u = (User) query.uniqueResult();
			tx.commit();
			sess.close();
			System.out.println("User in Dao " + u);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User By email exception " + e);
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User validateUser(UserLoginBean user) {
		User u = new User();
		
		try {
			Session sess = factory.openSession();
			Query<User> query = sess.createQuery("from User where email=?1 and password=?2");
			query.setParameter(1, user.getEmail());
			query.setParameter(2, user.getPassword());
			u = query.uniqueResult();		
			sess.clear();
			sess.close();			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception "+e);
		}
		
		return u;
	}

}
