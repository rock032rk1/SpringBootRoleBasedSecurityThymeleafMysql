package com.springbootrolebasedsecurity.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootrolebasedsecurity.app.model.Product;
import com.springbootrolebasedsecurity.app.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepo;

	@GetMapping("/")
	public String productPage(Model model, RedirectAttributes rd) {

		List<Product> plist = productRepo.findAll();
		model.addAttribute("plist", plist);

		rd.addFlashAttribute("login", "Login Successfully");

		// return "addproduct";
		return "product";
	}

	@GetMapping("/product")
	public String showList(Model model) {
		
		List<Product> plist = productRepo.findAll();
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

	@GetMapping("/getupdateId")
	public String updateProductPage(Integer id, Model model) {

		List<Product> plist = productRepo.findAll();
		Product p = plist.stream().filter(product -> id.equals(product.getId())).findAny().orElse(null);

		model.addAttribute("p", p);

		return "upproduct";
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
	
	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}
}
