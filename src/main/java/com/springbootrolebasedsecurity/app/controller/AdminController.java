package com.springbootrolebasedsecurity.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbootrolebasedsecurity.app.model.CIty;
import com.springbootrolebasedsecurity.app.model.Country;
import com.springbootrolebasedsecurity.app.model.Roles;
import com.springbootrolebasedsecurity.app.model.State;
import com.springbootrolebasedsecurity.app.model.User;
import com.springbootrolebasedsecurity.app.repository.CitiRepository;
import com.springbootrolebasedsecurity.app.repository.CountryRepository;
import com.springbootrolebasedsecurity.app.repository.RolesRepository;
import com.springbootrolebasedsecurity.app.repository.StateRepository;
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
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CitiRepository cityRepo; 

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
	
//	@GetMapping("/userUpdateById")
//	public String getUpdatePage(Integer id,Model model) {
//		
//		List<User> ulist=userRepo.findAll();
//		User user=ulist.stream()
//				  .filter(u->id.equals(u.getId()))
//				  .findAny().orElse(null);
//				  
//		model.addAttribute("u", user);
//		
//		return "userupdate";
//	}
	
//	@GetMapping("/userUpdateById")
//	@ResponseBody
	public String getUserById(Integer id,Model model) {
		
//		Integer cid=1;
//		List<Country> clist=countryRepo.findAll();
//		Country c=clist.stream()
//				.filter(country->cid.equals(country.getId()))
//				.findAny()
//				.orElse(null);
		//Optional<Country> c=countryRepo.findById(cid);
		//List<State> slist=stateRepo.findAll();
		
//		model.addAttribute("c", c);
//		model.addAttribute("slist", slist);
		
		//return userRepo.findById(id);
		return "updateuser";
	}
	
	@PostMapping("/updateU")
	public String updateUser(User user,Roles r,Country c,State s,CIty ct,Model model,HttpServletRequest req) {
		
		//For User Save Operation
		String role=req.getParameter("role");
		r.setRoles(role);
		List<Roles> urole=new ArrayList<Roles>();
		urole.add(r);
		user.setId(Integer.parseInt(req.getParameter("id")));
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setRoles(urole);
		userRepo.save(user);
		
		//For Country Save Operation
		c.setCountry_Name(req.getParameter("country_Name"));
		c.setCountry_code(req.getParameter("country_code"));
		countryRepo.save(c);
		int id=c.getId();
		//For State Save Operation
		s.setState_name(req.getParameter("state_name"));
		s.setCountryid(id);
		stateRepo.save(s);
		List<State> slist=new ArrayList<State>();
		slist.add(s);
		c.setStates(slist);
		//For City Save Operation
		ct.setCity_name(req.getParameter("city_name"));
		ct.setStateid(id);
		cityRepo.save(ct);
		List<CIty> ctlist=new ArrayList<CIty>();
		ctlist.add(ct);
		s.setCities(ctlist);
		List <Object[]> ulist=jpa.updateById();
		model.addAttribute("plist", ulist);
		
		return "redirect:/userList";	
	}
	
	@GetMapping("/deleteuser")
	public String deleteUser(Integer id) {
		
		userRepo.deleteById(id);
		return "redirect:/userList";
	}

}
