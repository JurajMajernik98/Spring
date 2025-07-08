package com.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.data.jpa.domain.Invoice;
import com.data.jpa.domain.InvoiceRepository;
import com.data.jpa.domain.Customer;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
	private InvoiceRepository invoiceRepo;
	private CustomerService cusService;
	
	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepo, CustomerService cusService) {
		this.invoiceRepo = invoiceRepo;
		this.cusService = cusService;
	}
	
	public ResponseEntity<String> saveInvoice(Invoice invoice) {
		Optional<Customer> findCustomer = cusService.findCustomerWithProducts(invoice.getCustomer().getId());
		
		if (findCustomer.isEmpty()) {
			return ResponseEntity.badRequest().body("No such customer exists");
		}
		
		invoice.setIssuedDate(LocalDate.now());
		invoiceRepo.save(invoice);
		
		return ResponseEntity.ok("new invoice Created: " + invoice.getId() + "\n" + invoice.getCustomerName());
	}
	
	public Optional<Invoice> getInvoice(Long id) {
		return invoiceRepo.findById(id);
	}
	
	public List<Invoice> getInvoices(){
		return this.invoiceRepo.findAll();
	}

}
