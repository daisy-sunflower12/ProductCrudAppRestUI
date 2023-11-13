package com.spring.rest.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.spring.rest.bean.UserLoginBean;
import com.spring.rest.dao.UserDao;
import com.spring.rest.entity.User;

@RestController
public class LoginController {
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/")
	public  ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("/home")
	public ModelAndView home(@CookieValue("details") String name, Model m) {
		System.out.println(name);
		
		Gson gson = new Gson();		
		String jsonString = gson.toJson(name);
		System.out.println(jsonString);
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(name);
		JsonPrimitive emailPrimitive = jsonElement.getAsJsonObject().getAsJsonPrimitive("email");
		System.out.println(emailPrimitive);
		String email = emailPrimitive.getAsString();
		System.out.println("email: "+email);
		JsonPrimitive passwordPrimitive = jsonElement.getAsJsonObject().getAsJsonPrimitive("password");
		System.out.println(passwordPrimitive);
		
		User userByEmail = userDao.getUserByEmail(email);
		int id = userByEmail.getUserRole().getId();
		String roleName = userByEmail.getUserRole().getName();
		System.out.println("Role Id "+id);
		System.out.println(roleName);
		m.addAttribute("roles", id);
		m.addAttribute("roleName", roleName);
		ModelAndView mav = new ModelAndView("home");
		
		return mav;
		
	}
	

	
	@RequestMapping(value="/login", method=RequestMethod.POST , consumes=MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> loginUser(@RequestBody UserLoginBean user){
		
		System.out.println("User: "+user);
		User login = userDao.validateUser(user);
		System.out.println("User "+login);
		
		if (login != null && login.getPassword().equals(user.getPassword())) {
			System.out.println("Helo");
			System.out.println("Email: "+login.getEmail());
			return ResponseEntity.ok(login);
		}else {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletResponse response) {
	Cookie cookie = new Cookie("details", "");
	cookie.setPath("/");
	cookie.setMaxAge(0);
	response.addCookie(cookie);
	
	}
}
