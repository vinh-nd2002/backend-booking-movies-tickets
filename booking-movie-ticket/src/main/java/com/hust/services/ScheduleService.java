package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hust.entity.Schedule;
import com.hust.repository.IScheduleRepository;

@Service
public class ScheduleService implements IScheduleService {
	@Autowired
	private IScheduleRepository scheduleRepository;

	@Override
	public Page<Schedule> getAllSchedules(Pageable pageable) {
		return scheduleRepository.findAll(pageable);
	}

}
