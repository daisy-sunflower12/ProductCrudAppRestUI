package com.spring.rest.bean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.spring.rest.dao.UserDao;
import com.spring.rest.entity.User;

@Component
public class CookieUtil {

	@Autowired
	private UserDao userDao;

	// A mapper to convert JSON string to User object
	  private static final ObjectMapper mapper = new ObjectMapper ();

	  // The name of the cookie that stores the user information
	  private static final String USER_COOKIE = "details";

	  // A method to get the user from the cookie
	  public User getUserFromCookie (HttpServletRequest request) {
	    // Get the cookies from the request
	    Cookie [] cookies = request.getCookies();
	    // Check if there are any cookies
	    if (cookies != null) {
	      // Loop through the cookies
	      for (Cookie cookie : cookies) {
	        // Check if the cookie name matches the user cookie
	        if (cookie.getName ().equals (USER_COOKIE)) {
	          try {
						// Get the cookie value and parse it as a JSON string
						String value = cookie.getValue();
						System.out.println("cookie value "+new java.net.URI(value).getPath());
						String path = new java.net.URI(value).getPath();
						// User user = mapper.readValue(value, User.class);
//						System.out.println(user);
						// Return the user object
						Gson gson = new Gson();		
						String jsonString = gson.toJson(path);
						System.out.println(jsonString);
						JsonParser jsonParser = new JsonParser();
						JsonElement jsonElement = jsonParser.parse(path);
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
//						m.addAttribute("roles", id);
//						m.addAttribute("roleName", roleName);
						//User user = userDao.getUserByEmail(value);
						System.out.println("User from cookie: "+userByEmail);
						return userByEmail;
						// userDao user;
					} catch (Exception e) {
						// Handle any exception
						e.printStackTrace();
					}
				}
			}
		}
		// Return null if no cookie is found or an exception occurs
		return null;
	}
}