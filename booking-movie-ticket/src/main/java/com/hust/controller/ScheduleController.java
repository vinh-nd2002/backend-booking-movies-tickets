package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.ScheduleDTO;
import com.hust.entity.Schedule;
import com.hust.services.IScheduleService;

@RestController
@RequestMapping(value = "api/v1/schedules")
@CrossOrigin("*")
public class ScheduleController {

	@Autowired
	private IScheduleService scheduleService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAllSchedules() {

		List<Schedule> schedules = scheduleService.getAllSchedules();

		List<ScheduleDTO> dtos = modelMapper.map(schedules, new TypeToken<List<ScheduleDTO>>() {
		}.getType());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
