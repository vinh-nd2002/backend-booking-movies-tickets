package com.hust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hust.entity.Room;
import com.hust.repository.IRoomRepository;

@Service
public class RoomService implements IRoomService{
	@Autowired
	private IRoomRepository roomRepository;

	@Override
	public Page<Room> getAllRooms(Pageable pageable) {
		return roomRepository.findAll(pageable);
	}

}
