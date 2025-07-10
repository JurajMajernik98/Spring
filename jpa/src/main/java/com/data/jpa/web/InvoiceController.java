package com.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.jpa.service.InvoiceService;

import jakarta.validation.Valid;

import com.data.jpa.domain.Invoice;

import java.util.List;

@RestController
public class InvoiceController {

	private InvoiceService invService;
	
	@Autowired
	public InvoiceController(InvoiceService invService) {
		this.invService = invService;
	}
	
	@GetMapping("/user/invoices")
	public List<Invoice> showInvoices(){
		return this.invService.getInvoices();
	}
	
	@PostMapping("/user/invoice")
	public ResponseEntity<String> createNew(@RequestBody @Valid Invoice invoice){
		return this.invService.saveInvoice(invoice);
	}
}
