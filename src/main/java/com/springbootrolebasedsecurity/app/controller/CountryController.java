package com.springbootrolebasedsecurity.app.controller;

import java.util.List;

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
import com.springbootrolebasedsecurity.app.model.State;
import com.springbootrolebasedsecurity.app.repository.CitiRepository;
import com.springbootrolebasedsecurity.app.repository.CountryRepository;
import com.springbootrolebasedsecurity.app.repository.JpaQuery;
import com.springbootrolebasedsecurity.app.repository.StateRepository;

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
	
	
	//@RequestMapping(method = RequestMethod.GET)
	@GetMapping("/userUpdateById")
	public String index(ModelMap modelMap) {
//		int id=1;
//		List<State> slist=jpaRepo.findByCountry(id);
		modelMap.put("countries", countryRepo.findAll());
		return "updateuser";
	}

	@RequestMapping(value = "/loadStatesByCountry/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String loadStatesByCountry(@PathVariable("id") int id) {
		Gson gson = new Gson();
		return gson.toJson(jpaRepo.findByCountry(id));
		//return jpaRepo.findByCountry(id);
	}

	@RequestMapping(value = "/loadCitiesByState/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String loadCitiesByState(@PathVariable("id") int id) {
		Gson gson = new Gson();
		return gson.toJson(jpaRepo.findByState(id));
		//return null;
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


}
