package com.hust.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.AccountDTO;
import com.hust.entity.Account;
import com.hust.services.IAccountService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/accounts")
//@CrossOrigin("http://127.0.0.1:5500")
public class AccountController {
	@Autowired
	private IAccountService iAccountService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public ResponseEntity<?> getAllAccounts(Pageable pageable) {
		Page<Account> accounts = iAccountService.getAllAccounts(pageable);

		List<AccountDTO> dtos = modelMapper.map(accounts.getContent(), new TypeToken<List<AccountDTO>>() {
		}.getType());

		Page<AccountDTO> dtoPages = new PageImpl<>(dtos, pageable, accounts.getTotalElements());
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable(name = "id") int id) {
		Account account = iAccountService.getAccountById(id);

		AccountDTO dto = modelMapper.map(account, AccountDTO.class);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
