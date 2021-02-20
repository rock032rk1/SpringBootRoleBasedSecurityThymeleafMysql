package com.springbootrolebasedsecurity.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Role_DB")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int role_id;
	private String roles;
	
	public Roles() {
		// TODO Auto-generated constructor stub
	}

	public Roles(String roles) {
		super();
		this.roles = roles;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
