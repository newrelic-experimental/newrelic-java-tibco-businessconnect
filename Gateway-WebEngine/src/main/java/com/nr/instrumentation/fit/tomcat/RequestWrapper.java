package com.nr.instrumentation.fit.tomcat;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.newrelic.api.agent.ExtendedRequest;
import com.newrelic.api.agent.HeaderType;

public class RequestWrapper extends ExtendedRequest {
	
	private HttpServletRequest request = null;
	
	public RequestWrapper(HttpServletRequest req) {
		request = req;
	}

	@Override
	public String getRequestURI() {
		return request.getRequestURI();
	}

	@Override
	public String getRemoteUser() {
		return request.getRemoteUser();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return request.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	@Override
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	@Override
	public String getCookieValue(String name) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public String getHeader(String name) {
		return request.getHeader(name);
	}

	@Override
	public String getMethod() {
		return request.getMethod();
	}

}
