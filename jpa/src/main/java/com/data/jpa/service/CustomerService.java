package com.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.data.jpa.domain.*;

@Service
public class CustomerService {
	private CustomerRepository cusRepo;
	
	@Autowired
	public CustomerService(CustomerRepository cusRepo) {
		this.cusRepo = cusRepo;
	}
//	najde dostane id pre customera a potom vrati aj vsetky jeho objekty typu Product
	public Optional<Customer> findCustomerWithProducts(Long id) {
		return cusRepo.findById(id);
	}
	
	public void saveCustomer(Customer customer) {
		cusRepo.save(customer);
	}
	
}
