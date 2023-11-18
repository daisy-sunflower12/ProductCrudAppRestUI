package com.spring.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rest.bean.CookieUtil;
import com.spring.rest.bean.ProductBean;
import com.spring.rest.dao.ProductDao;
import com.spring.rest.entity.Product;
import com.spring.rest.entity.ProductList;
import com.spring.rest.entity.User;

@RestController
public class SellerController {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CookieUtil cookieUtil;

	@RequestMapping(value = "/seller")
	public ModelAndView sellerPage() {
		ModelAndView mav = new ModelAndView("seller");
		return mav;
	}

	@RequestMapping(value = "/addProduct")
	public ModelAndView addProduct(HttpServletRequest request, Model m) {
		ModelAndView mav = new ModelAndView("addProduct");
		User userFromCookie = cookieUtil.getUserFromCookie(request);
		System.out.println("User: " + userFromCookie);
		System.out.println("Helloooooo");
		if (userFromCookie == null) {
			mav.setViewName("redirect:/");
			return mav;
		} else {
			System.out.println("User From Cookie: " + userFromCookie);
			int id = userFromCookie.getId();
			m.addAttribute("roleId", id);
			System.out.println("Role id in ctrl: " + userFromCookie.getUserRole().getId());
		}

		return mav;
	}
	
//	/@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, )

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductBean> addProduct(@RequestBody ProductBean product, HttpServletRequest request,
			Model m) {
		User userFromCookie = cookieUtil.getUserFromCookie(request);
		if (userFromCookie == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("User From Cookie: " + userFromCookie);
			int id = userFromCookie.getUserRole().getId();
			m.addAttribute("roleId", id);
			System.out.println("Role id in ctrl: " + userFromCookie.getUserRole().getId());
			System.out.println("Creating product");
			System.out.println("Product " + product);
			return new ResponseEntity<ProductBean>(productDao.createProduct(product), HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/viewproducts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductList getAllProducts(HttpServletRequest request, Model m) {
		User userFromCookie = cookieUtil.getUserFromCookie(request);

		System.out.println("User From Cookie: " + userFromCookie);
		int uId = userFromCookie.getId();
		System.out.println("User Id " + uId);
		m.addAttribute("uId", uId);
		return new ProductList(productDao.getAllProducts(uId));
	}
	
	@RequestMapping(value = "/allProducts")
	public ModelAndView products(HttpServletRequest request, Model m) {
		
		User userFromCookie = cookieUtil.getUserFromCookie(request);

		System.out.println("User From Cookie: " + userFromCookie);
		int uId = userFromCookie.getId();
		System.out.println("User Id " + uId);
		m.addAttribute("uId", uId);
		ModelAndView mav = new ModelAndView("viewproducts");
		return mav;
	}
	
	@PutMapping("/editProduct/{id}")
	public Product editProduct(@RequestBody @Valid Product product, @PathVariable("id") Integer id) {
		System.out.println("Id "+id);
		Product productById = productDao.getProductById(id);
		System.out.println("Product By id: "+productById);
		Integer id2 = productById.getUser().getId();
		User u = new User();
		u.setId(id2);
		product.setUser(u);
		System.out.println("Seller Id: "+id2);
		System.out.println("Product "+product);
		return productDao.updateProduct(product);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public void deleteProduct(@PathVariable("id") Integer id) {
		System.out.println("Selected Id: "+id);
		productDao.removeProduct(id);
	}
	
	//@RequestMapping("/")
}
