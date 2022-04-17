package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.Cinema;

public interface ICinemaRepository extends JpaRepository<Cinema, Integer>{

}
