package com.springbootrolebasedsecurity.app.repository;

import java.util.List;

import com.springbootrolebasedsecurity.app.model.Roles;
import com.springbootrolebasedsecurity.app.model.User;

public interface JpaQuery {

	public List<Object[]> JpaList();
	public List<Object[]> updateById();
	
}
