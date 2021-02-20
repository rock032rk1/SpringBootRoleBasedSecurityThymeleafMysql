package com.springbootrolebasedsecurity.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootrolebasedsecurity.app.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer>{

}
