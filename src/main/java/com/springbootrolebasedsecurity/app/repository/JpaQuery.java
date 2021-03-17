package com.springbootrolebasedsecurity.app.repository;

import java.util.List;

import com.springbootrolebasedsecurity.app.model.CIty;
import com.springbootrolebasedsecurity.app.model.State;

public interface JpaQuery {

	public List<Object[]> JpaList();
	public List<Object[]> updateById();
	public List<State> findByCountry(int id);
	public List<CIty> findByState(int id);
	public List<CIty> findStateById(int id);
	
}
