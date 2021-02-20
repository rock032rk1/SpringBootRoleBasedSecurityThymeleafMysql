package com.springbootrolebasedsecurity.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springbootrolebasedsecurity.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

//	    @Transactional
//	    @Modifying(clearAutomatically = true)
//	    @Query("UPDATE Product u SET u.pname=:pname, u.categary=:categary, u.price=:price WHERE u.id = :id")
//	    public void updateProductById(@Param("id") Integer id);
}
