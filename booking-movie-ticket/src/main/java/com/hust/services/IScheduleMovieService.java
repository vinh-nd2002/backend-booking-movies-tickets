package com.hust.services;

import java.util.Date;

import com.hust.entity.ScheduleMovie;

public interface IScheduleMovieService {

	ScheduleMovie getScheduleMovieById(int id);

	void createScheduleMoive(int movieId, int roomId, int scheduleId, Date scheduleDate);
}
