package com.springbootrolebasedsecurity.app.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "City_DB")
public class CIty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String city_name;
	@ManyToOne()
	@JoinColumn(name = "stateid", insertable = false, updatable = false)
	private State state;

	private Integer stateid;

	public CIty() {
		// TODO Auto-generated constructor stub
	}

	public CIty(String city_name, State state, Integer stateid) {
		super();
		this.city_name = city_name;
		this.state = state;
		this.stateid = stateid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

}
