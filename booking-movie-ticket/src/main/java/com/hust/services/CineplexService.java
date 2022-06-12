package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.Cineplex;
import com.hust.repository.ICineplexRepository;

@Service
public class CineplexService implements ICineplexService {
	@Autowired
	private ICineplexRepository cineplexRepository;

	@Override
	public Cineplex getCineplexById(short id) {
		return cineplexRepository.getById(id);
	}

	@Override
	public List<Cineplex> getAllCineplexs() {
		return cineplexRepository.findAll();
	}

}
