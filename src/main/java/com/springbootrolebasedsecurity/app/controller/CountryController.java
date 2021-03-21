package com.springbootrolebasedsecurity.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.springbootrolebasedsecurity.app.model.CIty;
import com.springbootrolebasedsecurity.app.model.State;
import com.springbootrolebasedsecurity.app.model.User;
import com.springbootrolebasedsecurity.app.repository.CitiRepository;
import com.springbootrolebasedsecurity.app.repository.CountryRepository;
import com.springbootrolebasedsecurity.app.repository.JpaQuery;
import com.springbootrolebasedsecurity.app.repository.StateRepository;
import com.springbootrolebasedsecurity.app.repository.UserRepository;

@Controller
public class CountryController {

	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CitiRepository cityRepo;
	@Autowired
	private JpaQuery jpaRepo;
	@Autowired
	private UserRepository userRepo;
	
	

	@RequestMapping(value = "/loadStatesByCountry/{stateId}", method = RequestMethod.GET)
	@ResponseBody
	public String loadStatesByCountry(@PathVariable("stateId") Integer id) {
		Gson gson = new Gson();
	
		return gson.toJson(jpaRepo.findByCountry(id));
		
	}

	@RequestMapping(value = "/loadCitiesByState/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String loadCitiesByState(@PathVariable("id") int id) {
		Gson gson = new Gson();

		int cid=id;
		int tot=1+cid;
		return gson.toJson(jpaRepo.findByState(tot));
		
	}
	
	@RequestMapping("/ajaxwish")
	public String getWishPage() {
		
		return "wish";
	}
	
	@RequestMapping(value="/wish",method = RequestMethod.GET)
	@ResponseBody
	public String wish(final @RequestParam("name") String name) {
		if(null!=name) {
			return "Hello "+name;
		}else {
			return "";
		}
	}
	
	@RequestMapping(value="/helloajax",method = RequestMethod.POST)
	@ResponseBody
	public String wish() {
		
		return "Hello Ajax";
	}
	
	@RequestMapping(value="/getCityById/{id}",method = RequestMethod.GET)
	@ResponseBody
	public String getCityById(@PathVariable("id") Integer id) {
		List<CIty> clist=cityRepo.findAll();
		
		CIty c=clist.stream()
				.filter(city->id.equals(city.getId()))
				.findAny()
				.orElse(null);
		Gson gson = new Gson();
		return gson.toJson(c);
		
	}
	
	@GetMapping("/userUpdateById")
	@ResponseBody
	public User getUserById(Integer id) {
		
		List<User> ulist=userRepo.findAll();
		User u=ulist.stream()
				.filter(user->id.equals(user.getId()))
				.findAny()
				.orElse(null);
		
		return u;
	}


}
