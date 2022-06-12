package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.Seat;
import com.hust.repository.ISeatRepository;

@Service
public class SeatService implements ISeatService {

	@Autowired
	private ISeatRepository repository;

	@Override
	public List<Seat> getAllSeats() {
		return repository.findAll();
	}

}
