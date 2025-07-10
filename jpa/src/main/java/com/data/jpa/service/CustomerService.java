package com.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.data.jpa.domain.*;

@Service
public class CustomerService implements UserDetailsService{
	private CustomerRepository cusRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomerService(CustomerRepository cusRepo, PasswordEncoder passwordEncoder) {
		this.cusRepo = cusRepo;
		this.passwordEncoder = passwordEncoder;
	}
	public Optional<Customer> findCustomerWithProducts(Long id) {
		return cusRepo.findById(id);
	}
	
	public void saveCustomer(Customer customer) {
		customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
		cusRepo.save(customer);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = cusRepo.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("No such user created"));
				
		return User.withUsername(
				customer.getUsername())
				.password(customer.getPassword()
				)
				.roles("USER")
				.build();
	}
	
}
