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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.UserDTO;
import com.hust.entity.User;
import com.hust.form.create.UserForm;
import com.hust.services.IUserService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private IUserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public ResponseEntity<?> getAllUsers(Pageable pageable) {
		Page<User> users = userService.getAllUsers(pageable);

		List<UserDTO> dtos = modelMapper.map(users.getContent(), new TypeToken<List<UserDTO>>() {
		}.getType());

		Page<UserDTO> dtoPages = new PageImpl<>(dtos, pageable, users.getTotalElements());
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(name = "id") int id) {
		User user = userService.getUserById(id);

		UserDTO dto = modelMapper.map(user, UserDTO.class);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> createUser(@RequestBody UserForm form) {
		userService.createUser(form);

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);

	}

	@GetMapping("/active-user")
	// validate: check exists, check not expired
	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
		// active user
		userService.activeUser(token);

		return new ResponseEntity<>("Active success!", HttpStatus.OK);
	}
}
