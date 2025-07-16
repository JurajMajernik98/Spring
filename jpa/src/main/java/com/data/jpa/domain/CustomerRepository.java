package com.data.jpa.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByUsername(String username);
	
	@Query("SELECT c FROM Customer c WHERE c.username = ?#{ principal?.username }")
	public Customer findLogedInUser();
}
