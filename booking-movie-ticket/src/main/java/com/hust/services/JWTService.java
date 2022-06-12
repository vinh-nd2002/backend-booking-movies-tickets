package com.hust.services;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hust.dto.UserLoginDTO;
import com.hust.entity.User;

@Service
public class JWTService {

	private ModelMapper modelMapper;

	public JWTService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;

	}

	private static final long EXPIRATION_TIME_ACCESS_TOKEN = 10 * 6000 * 100000; // 10 phút
	private static final long EXPIRATION_TIME_REFRESH_TOKEN = 60 * 6000 * 100000; // 60 phút
	private static final String SECRET = "ngu_duy_vinh";

	public void addResponeToBody(User user, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes());
		String access_token = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_ACCESS_TOKEN))
				.withIssuer(request.getRequestURL().toString()).withClaim("role", user.getRole().toString())
				.sign(algorithm);

		String refresh_token = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_REFRESH_TOKEN))
				.withIssuer(request.getRequestURL().toString()).withClaim("role", user.getRole().toString())
				.sign(algorithm);

		UserLoginDTO userLoginDTO = modelMapper.map(user, UserLoginDTO.class);

		userLoginDTO.setAccessToken(access_token);
		userLoginDTO.setRefreshToken(refresh_token);

		// convert object to json
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(userLoginDTO);

		// return json
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}

}
