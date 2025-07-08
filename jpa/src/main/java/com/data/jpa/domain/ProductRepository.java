package com.data.jpa.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByName(String name);
	
	public Optional<Product>findById(long id);
	
	@Query("SELECT p FROM Product p WHERE p.price > :minPrice")
	public List<Product> findExpensiveProducts(@Param("minPrice") double price);
}
