package com.hust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.services.IUserService;

import java.util.List;

import com.hust.models.User;

@RestController
@RequestMapping(value = "api/v1/users")
//@CrossOrigin("http://127.0.0.1:5500")
public class UserController {
	@Autowired
	private IUserService iUserService;
	
	@GetMapping()
	public ResponseEntity<?> getAllUsers(){
		List<User> users = iUserService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
