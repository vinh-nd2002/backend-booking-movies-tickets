package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.Room;

public interface IRoomRepository extends JpaRepository<Room, Integer> {

}
