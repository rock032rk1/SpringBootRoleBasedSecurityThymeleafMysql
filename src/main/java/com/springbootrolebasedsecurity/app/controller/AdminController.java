package com.springbootrolebasedsecurity.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbootrolebasedsecurity.app.model.Roles;
import com.springbootrolebasedsecurity.app.model.User;
import com.springbootrolebasedsecurity.app.repository.RolesRepository;
import com.springbootrolebasedsecurity.app.repository.UserRepository;
import com.springbootrolebasedsecurity.app.service.JpaQueryJoin;

@Controller
public class AdminController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	@Autowired
	private JpaQueryJoin jpa;

//	@GetMapping("/userPage")
//	public String getRegistPage() {
//
//		return "registration";
//	}
	
	//@PostMapping("/saveUser")
	@RequestMapping(value="/saveUser", method= {RequestMethod.POST, RequestMethod.GET})
	public String saveUser(User user,HttpServletRequest req) {
		
		String pass=req.getParameter("password");
		String encodPass=passEncoder.encode(pass);
		user.setPassword(encodPass);
		Roles r=new Roles();
		String role=req.getParameter("role");
		r.setRoles(role);
		List<Roles> urole=new ArrayList<Roles>();
		urole.add(r);
		user.setRoles(urole);
		
		userRepo.save(user);
		
		return "login";
	}
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		
		List <Object[]> user=jpa.updateById();
		model.addAttribute("plist", user);
		
		return "userList";
	}
	
	@GetMapping("/userUpdateById")
	public String getUpdatePage(Integer id,Model model) {
		
		List<User> ulist=userRepo.findAll();
		User user=ulist.stream()
				  .filter(u->id.equals(u.getId()))
				  .findAny().orElse(null);
				  
		model.addAttribute("u", user);
		
		//return "userUpdateForm";
		return "userupdate";
	}
	
	@PostMapping("/updateU")
	public String updateUser(User user,Roles r,Model model,HttpServletRequest req) {
		
		String role=req.getParameter("role");
		r.setRoles(role);
		List<Roles> urole=new ArrayList<Roles>();
		urole.add(r);
		user.setId(Integer.parseInt(req.getParameter("id")));
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setRoles(urole);
		userRepo.save(user);
		List <Object[]> ulist=jpa.updateById();
		model.addAttribute("plist", ulist);
		
		return "userList";	
	}
	
	@GetMapping("/deleteuser")
	public String deleteUser(Integer id) {
		
		userRepo.deleteById(id);
		return "redirect:/userList";
	}

}
