package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.RoomDTO;
import com.hust.entity.Room;
import com.hust.services.IRoomService;

@RestController
@RequestMapping(value = "api/v1/rooms")
public class RoomController {
	@Autowired
	private IRoomService iRoomService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAllRooms() {
		List<Room> rooms = iRoomService.getAllRooms();

		List<RoomDTO> dtos = modelMapper.map(rooms, new TypeToken<List<RoomDTO>>() {
		}.getType());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
