package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.Seat;

public interface ISeatRepository extends JpaRepository<Seat, Integer> {

}
