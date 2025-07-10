package com.data.jpa.service;
import com.data.jpa.domain.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private ProductRepository repository;
	private CustomerService cusService;
	
	
	@Autowired
	public ProductService(ProductRepository repository, CustomerService cusService) {
		this.repository = repository;
		this.cusService = cusService;
	}
	
	public Page<Product> getProducts(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public ResponseEntity<Product> save(Product prod) {
		Optional<Customer> checkCustomer = cusService.findCustomerWithProducts(prod.getCustomer().getId());
		

		if (checkCustomer.isPresent()) {
			repository.save(prod);
			return ResponseEntity.ok(prod);
		}
		
		return ResponseEntity.badRequest().body(prod);
	}
	
	public Optional<Product> findById(long id){
		return repository.findById(id);
	}
	
	public List<Product> findByName(String name){
		return repository.findByName(name);
	}
	
	public List<Product> findProductMinPrice(double amount){
		return this.repository.findExpensiveProducts(amount);
	}
}
