package com.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.jpa.service.*;
import java.util.Optional;
import com.data.jpa.domain.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private CustomerService cusService;
	
	@Autowired
	public CustomerController(CustomerService cusService) {
		this.cusService = cusService;
	}
	
	@GetMapping("/{id}")
	public Optional<Customer> getCustomer(@PathVariable(name = "id") Long id){
		return cusService.findCustomerWithProducts(id);
	}
	
	@PostMapping
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
		String message = "new Customer added: " + customer.getName();
		
		cusService.saveCustomer(customer);
		return ResponseEntity.ok(message);
	}

}
