package com.hust.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hust.entity.Cineplex;
//import com.hust.form.update.CineplexForm;

public interface ICineplexService {

	Cineplex getCineplexById(short id);

	List<Cineplex> getAllCineplexs();

	void createCineplex(MultipartFile imageForm, String cineplexCode, String cineplexName);

//	void updateCineplex(CineplexForm cineplexForm);

}
