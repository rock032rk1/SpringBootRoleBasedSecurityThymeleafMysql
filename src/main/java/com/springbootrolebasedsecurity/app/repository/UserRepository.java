package com.springbootrolebasedsecurity.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootrolebasedsecurity.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}

