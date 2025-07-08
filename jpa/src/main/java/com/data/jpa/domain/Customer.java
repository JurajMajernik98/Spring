package com.data.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
//	mapovanie na atribut vo foreign classe (Product)
	@OneToMany(mappedBy = "customer")
	@JsonManagedReference
	private List<Product> products;
	
	@OneToMany(mappedBy = "customer")
	@JsonManagedReference
	private List<Invoice> invoices;
	
	protected Customer() {};
		
	public Customer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getId() {
		return this.id;
	}
	
	public List<Product> getProducts(){
		return this.products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Invoice> getInvoices() {
		return this.invoices;
	}
	
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
}
