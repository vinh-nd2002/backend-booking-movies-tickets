package com.hust.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.modelmapper.TypeToken;

import com.hust.dto.CineplexDTO;
import com.hust.entity.Cineplex;
//import com.hust.form.update.CineplexForm;
import com.hust.services.ICineplexService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "api/v1/cineplexs")
@CrossOrigin("*")
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

//		for (CineplexDTO dto : dtos) {
//			for (CineplexDTO.CinemaDTO cinemaDTO : dto.getCinemas()) {
//				cinemaDTO.add(
//						linkTo(methodOn(CinemaController.class).getCinemaById(cinemaDTO.getCinemaId())).withSelfRel());
//			}
//			dto.add(linkTo(methodOn(CineplexController.class).getCineplexById(dto.getCineplexId())).withSelfRel());
//		}

		dtos.stream().forEach(item -> {
			item.add(linkTo(methodOn(CineplexController.class).getCineplexById(item.getCineplexId())).withSelfRel());
		});
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCineplexById(@PathVariable(name = "id") short id) {
		Cineplex cineplex = iCineplexService.getCineplexById(id);

		CineplexDTO dto = modelMapper.map(cineplex, CineplexDTO.class);

//		for (CineplexDTO.CinemaDTO cinemaDTO : dto.getCinemas()) {
//			cinemaDTO
//					.add(linkTo(methodOn(CinemaController.class).getCinemaById(cinemaDTO.getCinemaId())).withSelfRel());
//		}
		dto.getCinemas().stream().forEach(item -> {
			item.add(linkTo(methodOn(CinemaController.class).getCinemaById(item.getCinemaId())).withSelfRel());
		});
		dto.add(linkTo(methodOn(CineplexController.class).getCineplexById(id)).withSelfRel());

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<String> createCineplex(@RequestParam(name = "imageForm") MultipartFile imageForm,
			@RequestParam(name = "cineplexCode") String cineplexCode,
			@RequestParam(name = "cineplexName") String cineplexName) throws Exception {
		iCineplexService.createCineplex(imageForm, cineplexCode, cineplexName);

		return new ResponseEntity<>(" Create Success", HttpStatus.OK);
	}

//	@PutMapping(value = "/{id}")
//	public ResponseEntity<String> updateCineplex(@RequestBody CineplexForm cineplexForm) {
//		iCineplexService.updateCineplex(cineplexForm);
//		return new ResponseEntity<>("Update Success", HttpStatus.OK);
//	}
}
