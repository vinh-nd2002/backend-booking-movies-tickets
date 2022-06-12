package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.ScheduleMovie;

public interface IScheduleMovieRepository extends JpaRepository<ScheduleMovie, Integer> {

}
