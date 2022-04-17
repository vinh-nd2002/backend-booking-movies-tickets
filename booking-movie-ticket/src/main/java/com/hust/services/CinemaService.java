package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hust.entity.Cinema;
import com.hust.repository.ICinemaRepository;

@Service
public class CinemaService implements ICinemaService{
	
	@Autowired
	private ICinemaRepository cinemaRepository;

	@Override
	public Page<Cinema> getAllCinemas(Pageable pageable) {
		return cinemaRepository.findAll(pageable);
	}
}
