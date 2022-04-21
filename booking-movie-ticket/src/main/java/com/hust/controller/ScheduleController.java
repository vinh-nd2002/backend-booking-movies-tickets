package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.ScheduleDTO;
import com.hust.entity.Schedule;
import com.hust.services.IScheduleService;

@RestController
@RequestMapping(value = "api/v1/schedules")
public class ScheduleController {
	@Autowired
	private IScheduleService iScheduleService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAllSchedules(Pageable pageable) {
		Page<Schedule> schedules = iScheduleService.getAllSchedules(pageable);

		List<ScheduleDTO> dtos = modelMapper.map(schedules.getContent(), new TypeToken<List<ScheduleDTO>>() {
		}.getType());

		Page<ScheduleDTO> dtoPages = new PageImpl<>(dtos, pageable, schedules.getTotalElements());
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}
}
