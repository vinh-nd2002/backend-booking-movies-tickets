package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.Schedule;

public interface IScheduleRepository extends JpaRepository<Schedule, Integer>{

}
