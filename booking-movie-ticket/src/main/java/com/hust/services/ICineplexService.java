package com.hust.services;

import java.util.List;

import com.hust.entity.Cineplex;

public interface ICineplexService {

	Cineplex getCineplexById(short id);

	List<Cineplex> getAllCineplexs();

}
