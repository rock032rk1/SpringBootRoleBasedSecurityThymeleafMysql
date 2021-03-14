package com.springbootrolebasedsecurity.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "State_DB")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String state_name;

	@ManyToOne
	@JoinColumn(name = "countryid", insertable = false, updatable = false)
	private Country country;

	private Integer countryid;
	@OneToMany(mappedBy = "state")
	private List<CIty> cities;

	public State() {
		// TODO Auto-generated constructor stub
	}

	public State(String state_name, Country country, Integer countryid, List<CIty> cities) {
		super();
		this.state_name = state_name;
		this.country = country;
		this.countryid = countryid;
		this.cities = cities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getCountryid() {
		return countryid;
	}

	public void setCountryid(Integer countryid) {
		this.countryid = countryid;
	}

	public List<CIty> getCities() {
		return cities;
	}

	public void setCities(List<CIty> cities) {
		this.cities = cities;
	}

}
