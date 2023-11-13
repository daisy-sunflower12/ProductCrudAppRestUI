package com.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rest.bean.UserBean;
import com.spring.rest.dao.UserDao;
import com.spring.rest.entity.RoleList;
import com.spring.rest.entity.User;

@RestController
public class HomeController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/u7h6yt")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
	
	@GetMapping(value="/register")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public RoleList getRoles(){
		return new RoleList(userDao.getRoles());		
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserBean> createStudent(@RequestBody UserBean user){
		User userByEmail = userDao.getUserByEmail(user.getEmail());
			
		if (null != userByEmail && userByEmail.getEmail().equals(user.getEmail())) {
			System.out.println("Email already exists");
			return new ResponseEntity<UserBean>(HttpStatus.BAD_REQUEST);
		}else {
		return new ResponseEntity<UserBean>(userDao.registerUser(user), HttpStatus.CREATED);
		}
	}
	
	
	
	
}
