package com.hust.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hust.entity.Cinema;

public interface ICinemaService {

	Page<Cinema> getAllCinemas(Pageable pageable);

	Cinema getCinemaById(int id);

}
