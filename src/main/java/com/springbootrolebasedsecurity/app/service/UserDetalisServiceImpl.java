package com.springbootrolebasedsecurity.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springbootrolebasedsecurity.app.model.MyUserDetails;
import com.springbootrolebasedsecurity.app.model.User;
import com.springbootrolebasedsecurity.app.repository.UserRepository;
@Service
public class UserDetalisServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepo.findByUsername(username);
		MyUserDetails userDetails=null;
		
		if(user!=null) 
		{
			userDetails=new MyUserDetails();
			userDetails.setUser(user);
		}
		else 
		{
			throw new UsernameNotFoundException("User Not exits " + username);
		}
		
		return userDetails;
	}

}
