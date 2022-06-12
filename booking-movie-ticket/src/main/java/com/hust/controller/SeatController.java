package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.SeatDTO;
import com.hust.entity.Seat;
import com.hust.services.ISeatService;

@RestController
@RequestMapping(value = "api/v1/seats")
@CrossOrigin("*")
public class SeatController {
	@Autowired
	private ISeatService iSeatService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllAccounts() {
		List<Seat> seats = iSeatService.getAllSeats();

		List<SeatDTO> dtos = modelMapper.map(seats, new TypeToken<List<SeatDTO>>() {
		}.getType());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
