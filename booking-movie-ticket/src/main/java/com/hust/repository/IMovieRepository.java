package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.Movie;

public interface IMovieRepository extends JpaRepository<Movie, Integer>{

}
