package com.hust.services;

import java.util.List;

import com.hust.entity.Room;

public interface IRoomService {

	List<Room> getAllRooms();

	Room getRoomById(int id);

}
