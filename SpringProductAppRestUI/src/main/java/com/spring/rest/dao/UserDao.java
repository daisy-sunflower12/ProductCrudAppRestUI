package com.spring.rest.dao;

import java.util.List;

import com.spring.rest.bean.UserBean;
import com.spring.rest.bean.UserLoginBean;
import com.spring.rest.entity.User;
import com.spring.rest.entity.UserRole;

public interface UserDao {

	public User registerUser(User user);

	public UserBean registerUser(UserBean user);

	public List<UserRole> getRoles();

	public User getUserByEmail(String email);
	
	public User validateUser(UserLoginBean user);

}
