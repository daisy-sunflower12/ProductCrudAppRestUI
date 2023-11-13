package com.spring.rest.entity;

import java.util.List;

public class RoleList {

	private List<UserRole> roles;

	public RoleList() {
		super();
	}

	public RoleList(List<UserRole> roles) {
		super();
		this.roles = roles;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
	
	
}
