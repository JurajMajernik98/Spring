package com.data.jpa.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Invoices")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	private String customerName;
	
	@NotNull(message = "cannot be empty")
	@Positive(message = "must be positive")
	private double amount;
	private LocalDate issuedDate;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;
	
	protected Invoice() {}
	
	//this is completely useless if not used internally in code 
	public Invoice(String customerName, double amount) {
		this.customerName = customerName;
		this.amount = amount;
		this.issuedDate = LocalDate.now();
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setCustomerName(String name) {
		this.customerName = name;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public LocalDate getIssuedDate() {
		return this.issuedDate;
	}
	
	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
