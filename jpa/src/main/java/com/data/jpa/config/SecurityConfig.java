package com.data.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.data.jpa.service.CustomerService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		http
		.csrf(t -> t.disable())
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/store/**").permitAll()
				.requestMatchers("/user/**").authenticated()
				).httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authManager(CustomerService customerService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(customerService);
		
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		
		return new ProviderManager(authenticationProvider);
	}
}
