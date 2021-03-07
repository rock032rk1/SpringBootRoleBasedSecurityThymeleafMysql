package com.springbootrolebasedsecurity.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springbootrolebasedsecurity.app.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
//	@Transactional
//	@Query("SELECT u.id, u.username, r.roles FROM USER u INNER JOIN ROLES r ON u.id=r.id")
//	public List<User> join();
}

