package com.data.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.data.jpa.service.CustomerService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		http.csrf(t -> t.disable())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/store/**", "/login", "/basket/**").permitAll()
						.requestMatchers("/user/**", "/profile", "/profile/**").authenticated());

		return http.build();
	}

	@Bean
	public AuthenticationManager authManager(CustomerService customerService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(customerService);

		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler("/profile");
		handler.setAlwaysUseDefaultTargetUrl(true);
		return handler;
	}
	
	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
	
	@Bean SecurityContextRepository contextRepo() {
		return new HttpSessionSecurityContextRepository();
	}
}
