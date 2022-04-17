package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.TypeToken;

import com.hust.dto.CineplexDTO;
import com.hust.entity.Cineplex;
import com.hust.services.ICineplexService;

@RestController
@RequestMapping(value = "api/v1/cineplexs")
public class CineplexController {
	@Autowired
	private ICineplexService iCineplexService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAllCineplexs() {
		List<Cineplex> cineplexs = iCineplexService.getAllCineplexs();

		List<CineplexDTO> dtos = modelMapper.map(cineplexs, new TypeToken<List<CineplexDTO>>() {
		}.getType());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
