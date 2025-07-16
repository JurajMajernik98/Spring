package com.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.data.jpa.domain.Customer;

@Service
public class MailService {
	private JavaMailSender javaMail;
	
	@Autowired
	public MailService(JavaMailSender javaMail) {
		this.javaMail = javaMail;
	}
	
	public void creationConfirmation(Customer customer) {
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(customer.getUsername());
		msg.setSubject("Registration");
		msg.setText("Dear " + customer.getName() + "\nYou have successfully created new account using " + customer.getUsername());
		
		try {
			this.javaMail.send(msg);
		}catch(MailException ex){
			System.err.println(ex.getMessage());
		}
	}
}
