package com.data.jpa.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.jpa.service.ProductService;
import com.data.jpa.domain.Product;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store/products")
public class ProductController {
	private ProductService service;
	
	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@GetMapping
	public Page<Product> getProduct(Pageable pageable){
		return service.getProducts(pageable);
	}
	
	@PostMapping
	public ResponseEntity<Product> insertNew(@RequestBody Product product) {
		return service.save(product);
	}
	
	@GetMapping("/id/{id}")
	public Optional<Product> getProductById(@PathVariable(name = "id") long id) {
		return service.findById(id);
	}
	
	@GetMapping("/search")
	public List<Product> getProducts(@RequestParam(name = "name") String name){
		return service.findByName(name);
	}
	
	@GetMapping("/expensive")
	public List<Product> getProductsWithCertainPrice(@RequestParam(name = "min") double value){
		return this.service.findProductMinPrice(value);
	}
}
