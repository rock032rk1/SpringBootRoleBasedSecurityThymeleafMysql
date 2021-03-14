package com.springbootrolebasedsecurity.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootrolebasedsecurity.app.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
