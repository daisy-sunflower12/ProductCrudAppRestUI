package com.spring.rest.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CookieInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		System.out.println("I am pre Interceptor");
		String url = req.getRequestURL().toString();
		System.out.println("URL : " + url);

		if (url.endsWith(req.getContextPath() + "/") || url.contains("/login") || url.contains("/js")
				|| url.contains("/register"))
			return true;

		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("details")) {
					String cookieValue = cookie.getValue();
					System.out.println("Cookie from Interceptor: "+cookieValue);
					return true;
				}
			}

			res.sendRedirect(req.getContextPath() + "/");
			return false;
		} else {
			res.sendRedirect(req.getContextPath() + "/");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("I am post Interceptor");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
	}
}
