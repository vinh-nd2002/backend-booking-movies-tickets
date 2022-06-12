package com.hust.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

	private static final String SECRET = "ngu_duy_vinh";
	private static final String PREFIX_TOKEN = "Bearer ";
	private static final String AUTHORIZATION = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/api/v1/login")) {
			filterChain.doFilter(request, response);
		} else {
			String authorizationHeader = request.getHeader(AUTHORIZATION);
			if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX_TOKEN)) {
				try {
					String token = authorizationHeader.substring(PREFIX_TOKEN.length());
					Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes());
					JWTVerifier verifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = verifier.verify(token);
					String username = decodedJWT.getSubject();
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, Collections.emptyList());
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

					filterChain.doFilter(request, response);
				} catch (Exception e) {

				}

			} else {
				filterChain.doFilter(request, response);
			}
		}

	}

}
