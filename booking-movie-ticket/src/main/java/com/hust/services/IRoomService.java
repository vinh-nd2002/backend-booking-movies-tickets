package com.hust.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hust.entity.Room;

public interface IRoomService {

	Page<Room> getAllRooms(Pageable pageable);

}
