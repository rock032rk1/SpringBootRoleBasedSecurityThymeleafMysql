package com.springbootrolebasedsecurity.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootrolebasedsecurity.app.model.CIty;

public interface CitiRepository extends JpaRepository<CIty, Integer>{

}
