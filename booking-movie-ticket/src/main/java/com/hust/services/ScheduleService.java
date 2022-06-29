package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.Schedule;
import com.hust.repository.IScheduleRepository;

@Service
public class ScheduleService implements IScheduleService {

	@Autowired
	private IScheduleRepository scheduleRepository;

	@Override
	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}
}
