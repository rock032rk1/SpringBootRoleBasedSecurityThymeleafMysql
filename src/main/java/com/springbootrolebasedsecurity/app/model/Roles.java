package com.springbootrolebasedsecurity.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Role_DB")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String roles;
//	@ManyToOne
//	@JoinColumn(name = "uid", insertable = false, updatable = false)
//	private User user;
//	private Integer uid;

	public Roles() {
		// TODO Auto-generated constructor stub
	}

	public Roles(String roles) {
		super();
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
