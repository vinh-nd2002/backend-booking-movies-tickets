package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.Room;
import com.hust.repository.IRoomRepository;

@Service
public class RoomService implements IRoomService {
	@Autowired
	private IRoomRepository roomRepository;

	@Override
	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	@Override
	public Room getRoomById(int id) {
		return roomRepository.getById(id);
	}

}
