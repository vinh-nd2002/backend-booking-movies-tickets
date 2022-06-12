package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.ScheduleMovie;
import com.hust.repository.IScheduleMovieRepository;

@Service
public class ScheduleMovieService implements IScheduleMovieService {
	@Autowired
	private IScheduleMovieRepository scheduleMovieRepository;

	@Override
	public ScheduleMovie getScheduleMovieById(int id) {
		return scheduleMovieRepository.getById(id);
	}

}
