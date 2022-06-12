package com.hust.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.ScheduleMovieDTO;
import com.hust.entity.ScheduleMovie;

import com.hust.services.IScheduleMovieService;

@RestController
@RequestMapping(value = "api/v1/schedulemovies")
@CrossOrigin("*")
public class ScheduleMovieController {
	@Autowired
	private IScheduleMovieService iScheduleMovieService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getScheduleById(@PathVariable(name = "id") int id) {
		ScheduleMovie scheduleDetail = iScheduleMovieService.getScheduleMovieById(id);

		ScheduleMovieDTO dto = modelMapper.map(scheduleDetail, ScheduleMovieDTO.class);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
