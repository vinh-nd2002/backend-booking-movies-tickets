package com.hust.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hust.entity.User;
import com.hust.services.IUserService;
import com.hust.services.JWTService;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	private IUserService iAccountService;

	private ModelMapper modelMapper;

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager, IUserService iAccountService,
			ModelMapper modelMapper) {
		this.authenticationManager = authenticationManager;
		this.iAccountService = iAccountService;
		this.modelMapper = modelMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password, Collections.emptyList());
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		User user = iAccountService.findUserByUsername(authentication.getName());

		JWTService jwtService = new JWTService(modelMapper);
		jwtService.addResponeToBody(user, request, response);

	}
}
