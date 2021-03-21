package com.springbootrolebasedsecurity.app.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootrolebasedsecurity.app.model.Product;
import com.springbootrolebasedsecurity.app.repository.JpaQuery;
import com.springbootrolebasedsecurity.app.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private JpaQuery jpaRepo;

	@GetMapping("/")
	public String productPage(Model model, RedirectAttributes rd) {

		List<Product> plist = productRepo.findAll();
		model.addAttribute("plist", plist);

		rd.addFlashAttribute("login", "Login Successfully");

		return getPagination(1, model);
	}

	@GetMapping("/product")
	public String showList(Model model) {
		

		return getPagination(1, model);
		
	}
	
	@GetMapping("/product/{pageNo}")
	public String getPagination(@PathVariable(value = "pageNo") int pageNo,Model model) {
		
		int pageSize=2;
		Page<Product> page=jpaRepo.findPagination(pageNo, pageSize);
		List<Product> plist=page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalItem", page.getTotalElements());
		model.addAttribute("plist", plist);
		
		return "product";
	}

	@GetMapping("/new")
	public String getProductPage(Model model) {

		return "addproduct";
	}

	@PostMapping("/addProduct")
	public String saveProduct(Product product) {

		productRepo.save(product);

		return "redirect:/product";
	}

//	@GetMapping("/getupdateId")
//	public String updateProductPage(Integer id, Model model) {
//
//		List<Product> plist = productRepo.findAll();
//		Product p = plist.stream().filter(product -> id.equals(product.getId())).findAny().orElse(null);
//
//		model.addAttribute("p", p);
//
//		return "upproduct";
//	}
	
	@GetMapping("/getupdateId")
	@ResponseBody
	public Optional<Product> getProductById(Integer id) {
		
		return productRepo.findById(id);
	}

	@PostMapping("/updateP")
	public String updateProduct(Product product, HttpServletRequest req) {

		product.setId(Integer.parseInt(req.getParameter("id")));
		product.setPname(req.getParameter("pname"));
		product.setCategary(req.getParameter("categary"));
		product.setPrice(Float.parseFloat(req.getParameter("price")));

		productRepo.save(product);

		System.out.println("ID " + product.getId());

		return "redirect:/product";
	}

	@GetMapping("/delete")
	public String deleteProduct(Integer id, RedirectAttributes rd) {

		productRepo.deleteById(id);
		rd.addFlashAttribute("deletemsg", "Product delete Successfully");

		return "redirect:/product";
	}

	@GetMapping("/403")
	public String notAccessPage() {

		return "403";
	}
	
}
