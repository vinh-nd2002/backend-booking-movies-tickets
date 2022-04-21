package com.hust.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hust.entity.Schedule;

public interface IScheduleService {

	Page<Schedule> getAllSchedules(Pageable pageable);

}
