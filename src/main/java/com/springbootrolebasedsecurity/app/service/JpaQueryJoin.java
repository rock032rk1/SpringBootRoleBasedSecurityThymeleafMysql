package com.springbootrolebasedsecurity.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootrolebasedsecurity.app.model.Roles;
import com.springbootrolebasedsecurity.app.model.User;
import com.springbootrolebasedsecurity.app.repository.JpaQuery;
import com.springbootrolebasedsecurity.app.repository.UserRepository;
@Service
public class JpaQueryJoin implements JpaQuery{

	@Autowired
	private EntityManagerFactory emf;
	
	@Transactional
	public List<Object[]> JpaList() {
		// TODO Auto-generated method stub
		
		EntityManager em=emf.createEntityManager();
		String hql="SELECT u.id, u.username, r.roles FROM user_db u INNER JOIN role_db r ON u.id=r.id WHERE r.roles='USER' OR r.roles='EDITOR'";
		String hql1="SELECT u.id, u.username, r.roles FROM User u INNER JOIN Roles r ON u.id=r.id WHERE r.roles=:USER";
		@SuppressWarnings("unchecked")
		List<Object[]> query= em.createNativeQuery(hql).getResultList();
		@SuppressWarnings("unchecked")
		Query<Object[]> query1= (Query<Object[]>) em.createQuery(hql1);
		
		List<Object[]> ulist=query1.getResultList();
		
		em.close();
		
		return query;
	}

	@Transactional
	public List<Object[]> updateById() {
		// TODO Auto-generated method stub
		EntityManager em=emf.createEntityManager();
		String user="USER";
		String editor="EDITOR";
		String hql="SELECT u.id, u.username, u.password, r.roles FROM User u INNER JOIN Roles r ON u.id=r.id WHERE r.roles='"+user+"' OR r.roles='"+editor+"'";
		@SuppressWarnings("unchecked")
		Query<Object[]> query= (Query<Object[]>) em.createQuery(hql);
		List<Object[]> ulist=query.getResultList();
		
		em.close();
		
		return ulist;
	}


}