package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.Cinema;
import com.hust.repository.ICinemaRepository;

@Service
public class CinemaService implements ICinemaService {

	@Autowired
	private ICinemaRepository cinemaRepository;

	@Override
	public List<Cinema> getAllCinemas() {
		return cinemaRepository.findAll();
	}

	@Override
	public Cinema getCinemaById(int id) {
		return cinemaRepository.getById(id);
	}
}
