package com.hust.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.CinemaDTO;
import com.hust.dto.MovieDTO;
import com.hust.entity.Cinema;
import com.hust.services.ICinemaService;

@RestController
@RequestMapping(value = "api/v1/cinemas")
@CrossOrigin("*")
public class CinemaController {

	@Autowired
	private ICinemaService iCinemaService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAllCinemas() {
		// get all entities
		List<Cinema> cinemas = iCinemaService.getAllCinemas();

		// convert to dtos
		List<MovieDTO> dtos = modelMapper.map(cinemas, new TypeToken<List<CinemaDTO>>() {
		}.getType());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCinemaById(@PathVariable(name = "id") int id) {
		// get entity
		Cinema cinema = iCinemaService.getCinemaById(id);

		// convert to dto
		CinemaDTO dto = modelMapper.map(cinema, CinemaDTO.class);

		dto.add(linkTo(methodOn(CinemaController.class).getCinemaById(id)).withSelfRel());

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
