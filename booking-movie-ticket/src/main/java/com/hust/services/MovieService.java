package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.Movie;
import com.hust.repository.IMovieRepository;

@Service
public class MovieService implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie getMovieById(int id) {
		return movieRepository.getById(id);
	}

}
