package com.hust.services;

import java.util.List;

import com.hust.entity.Cinema;

public interface ICinemaService {

	List<Cinema> getAllCinemas();

	Cinema getCinemaById(int id);

}
