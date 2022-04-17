package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.CinemaDTO;
import com.hust.entity.Cinema;
import com.hust.services.ICinemaService;

@RestController
@RequestMapping(value = "api/v1/cinemas")
public class CinemaController {

	@Autowired
	private ICinemaService iCinemaService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAllCinemas(Pageable pageable) {
		Page<Cinema> cinemas = iCinemaService.getAllCinemas(pageable);

		List<CinemaDTO> dtos = modelMapper.map(cinemas.getContent(), new TypeToken<List<CinemaDTO>>() {
		}.getType());

		Page<CinemaDTO> dtoPages = new PageImpl<>(dtos, pageable, cinemas.getTotalElements());
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}
}