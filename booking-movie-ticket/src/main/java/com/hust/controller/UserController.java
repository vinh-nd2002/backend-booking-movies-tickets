package com.hust.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.UserDTO;
import com.hust.entity.User;
import com.hust.services.IUserService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private IUserService iUserService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public ResponseEntity<?> getAllUsers(Pageable pageable) {
		Page<User> users = iUserService.getAllUsers(pageable);

		List<UserDTO> dtos = modelMapper.map(users.getContent(), new TypeToken<List<UserDTO>>() {
		}.getType());

		Page<UserDTO> dtoPages = new PageImpl<>(dtos, pageable, users.getTotalElements());
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(name = "id") int id) {
		User user = iUserService.getUserById(id);

		UserDTO dto = modelMapper.map(user, UserDTO.class);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
