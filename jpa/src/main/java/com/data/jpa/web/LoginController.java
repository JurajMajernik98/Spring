package com.data.jpa.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.SecurityContextRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
	private AuthenticationManager authManager;
	private SecurityContextRepository securityContextRepository;
	private AuthenticationSuccessHandler authHandler;
	private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
			.getContextHolderStrategy();

	@Autowired
	public LoginController(AuthenticationManager authManager, SecurityContextRepository securityContextRepository,
			AuthenticationSuccessHandler authHandler) {
		this.authManager = authManager;
		this.securityContextRepository = securityContextRepository;
		this.authHandler = authHandler;
	}

	@PostMapping("/login")
	public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),
				loginRequest.password());
		Authentication authReponse = this.authManager.authenticate(authRequest);
		SecurityContext context = securityContextHolderStrategy.createEmptyContext();

		context.setAuthentication(authReponse);
		securityContextHolderStrategy.setContext(context);
		this.securityContextRepository.saveContext(context, request, response);
		this.authHandler.onAuthenticationSuccess(request, response, authReponse);
	}

	public record LoginRequest(String username, String password) {
	}
}
