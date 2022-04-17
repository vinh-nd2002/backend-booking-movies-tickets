package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.MovieDTO;
import com.hust.entity.Movie;
import com.hust.services.IMovieService;

@RestController
@RequestMapping(value = "/api/v1/movies")
public class MovieController {

	@Autowired
	private IMovieService iMovieService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAllMovies() {
		List<Movie> movies = iMovieService.getAllMovies();

		List<MovieDTO> dtos = modelMapper.map(movies, new TypeToken<List<MovieDTO>>() {
		}.getType());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
