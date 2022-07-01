package com.hust.services;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hust.entity.Movie;
import com.hust.form.filter.MovieFilterForm;

public interface IMovieService {

	List<Movie> getAllMovies(String search, MovieFilterForm filterForm);

	Movie getMovieById(int id);

	void createMovie(MultipartFile imageForm, String movieName, String movieDescription, String movieTrailer,
			Date movieRelease, short movieLength, short movieEvaluate, int moviePrice, boolean movieStatus);

	void deleteMovieById(int id) throws Exception;

	void updateMovie(MultipartFile imageForm, int movieId, String movieName, String movieDescription, String movieTrailer,
			Date movieRelease, short movieLength, short movieEvaluate, int moviePrice, boolean movieStatus) throws Exception;

}
