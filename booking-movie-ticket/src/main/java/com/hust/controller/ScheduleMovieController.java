package com.hust.controller;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.ScheduleMovieDTO;
import com.hust.entity.ScheduleMovie;
import com.hust.services.IBookingService;
import com.hust.services.IScheduleMovieService;

@RestController
@RequestMapping(value = "api/v1/schedule-movies")
@CrossOrigin("*")
public class ScheduleMovieController {
	@Autowired
	private IScheduleMovieService iScheduleMovieService;

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getScheduleById(@PathVariable(name = "id") int id) {
		ScheduleMovie scheduleDetail = iScheduleMovieService.getScheduleMovieById(id);

		ScheduleMovieDTO dto = modelMapper.map(scheduleDetail, ScheduleMovieDTO.class);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> createScheduleMoive(@RequestParam(name = "movieId") int movieId,
			@RequestParam(name = "roomId") int roomId, @RequestParam(name = "scheduleId") int scheduleId,
			@RequestParam(name = "scheduleDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduleDate) {
		iScheduleMovieService.createScheduleMoive(movieId, roomId, scheduleId, scheduleDate);
		return new ResponseEntity<>("Create successfully!", HttpStatus.CREATED);
	}

	@GetMapping("/success-ticket-booking")
	public ResponseEntity<?> sendMailBookingTicketSuccessViaEmail(
			@RequestParam(name = "scheduleMovieId") int scheduleMovieId, @RequestParam(name = "email") String email) {
		// send mail
		bookingService.sendSuccessfulTicketBookingViaEmail(email, scheduleMovieId);

		return new ResponseEntity<>("Send mail success!", HttpStatus.OK);
	}

}
