package com.hust.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.entity.Movie;
import com.hust.entity.Room;
import com.hust.entity.Schedule;
import com.hust.entity.ScheduleMovie;
import com.hust.repository.IMovieRepository;
import com.hust.repository.IRoomRepository;
import com.hust.repository.IScheduleMovieRepository;
import com.hust.repository.IScheduleRepository;

@Service
@Transactional
public class ScheduleMovieService implements IScheduleMovieService {
	@Autowired
	private IScheduleMovieRepository scheduleMovieRepository;

	@Autowired
	private IMovieRepository movieRepository;

	@Autowired
	private IRoomRepository roomRepository;

	@Autowired
	private IScheduleRepository scheduleRepository;

	@Override
	public ScheduleMovie getScheduleMovieById(int id) {
		return scheduleMovieRepository.getById(id);
	}

	@Override
	public void createScheduleMoive(int movieId, int roomId, int scheduleId, Date scheduleDate) {
		Room room = roomRepository.getById(roomId);
		Movie movie = movieRepository.getById(movieId);
		Schedule schedule = scheduleRepository.getById(scheduleId);
		ScheduleMovie scheduleMovie = new ScheduleMovie();
		scheduleMovie.setMovie(movie);
		scheduleMovie.setRoom(room);
		scheduleMovie.setSchedule(schedule);
		scheduleMovie.setScheduleDate(scheduleDate);
		scheduleMovieRepository.save(scheduleMovie);
	}

}
