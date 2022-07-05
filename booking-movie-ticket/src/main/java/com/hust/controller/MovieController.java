package com.hust.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hust.dto.MovieDTO;
import com.hust.entity.Movie;
import com.hust.form.filter.MovieFilterForm;
import com.hust.services.IMovieService;

@RestController
@RequestMapping(value = "api/v1/movies")
@CrossOrigin("*")
public class MovieController {

	@Autowired
	private IMovieService iMovieService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAllMovies(@RequestParam(value = "search", required = false) String search,
			MovieFilterForm filterForm) {
		List<Movie> movies = iMovieService.getAllMovies(search, filterForm);

		List<MovieDTO> dtos = modelMapper.map(movies, new TypeToken<List<MovieDTO>>() {
		}.getType());

		dtos.stream().forEach(item -> {
			item.add(linkTo(methodOn(MovieController.class).getMovieById(item.getMovieId())).withSelfRel());
		});
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable(name = "id") int id) {
		Movie movie = iMovieService.getMovieById(id);

		MovieDTO dto = modelMapper.map(movie, MovieDTO.class);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<String> createMovie(
			@RequestParam(name = "imageForm", required = true) MultipartFile imageForm,
			@RequestParam(name = "movieName") String movieName,
			@RequestParam(name = "movieDescription") String movieDescription,
			@RequestParam(name = "movieTrailer") String movieTrailer,
			@RequestParam(name = "movieRelease") @DateTimeFormat(pattern = "yyyy-MM-dd") Date movieRelease,
			@RequestParam(name = "movieLength") short movieLength,
			@RequestParam(name = "movieEvaluate") short movieEvaluate,
			@RequestParam(name = "moviePrice") int moviePrice, @RequestParam(name = "movieStatus") boolean movieStatus)
			throws Exception {
		iMovieService.createMovie(imageForm, movieName, movieDescription, movieTrailer, movieRelease, movieLength,
				movieEvaluate, moviePrice, movieStatus);

		return new ResponseEntity<>("Create Success", HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> updateMovie(@PathVariable(name = "id") int movieId,
			@RequestParam(name = "imageForm", required = false) MultipartFile imageForm,
			@RequestParam(name = "movieName") String movieName,
			@RequestParam(name = "movieDescription") String movieDescription,
			@RequestParam(name = "movieTrailer") String movieTrailer,
			@RequestParam(name = "movieRelease") @DateTimeFormat(pattern = "yyyy-MM-dd") Date movieRelease,
			@RequestParam(name = "movieLength") short movieLength,
			@RequestParam(name = "movieEvaluate") short movieEvaluate,
			@RequestParam(name = "moviePrice") int moviePrice, @RequestParam(name = "movieStatus") boolean movieStatus)
			throws Exception {
		iMovieService.updateMovie(imageForm, movieId, movieName, movieDescription, movieTrailer, movieRelease,
				movieLength, movieEvaluate, moviePrice, movieStatus);

		return new ResponseEntity<>("Update successfully", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable(name = "id") int id) throws Exception {
		iMovieService.deleteMovieById(id);

		return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
	}
}
