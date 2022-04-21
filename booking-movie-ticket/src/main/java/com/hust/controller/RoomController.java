package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	public ResponseEntity<?> getAllRooms(Pageable pageable) {
		Page<Room> rooms = iRoomService.getAllRooms(pageable);

		List<RoomDTO> dtos = modelMapper.map(rooms.getContent(), new TypeToken<List<RoomDTO>>() {
		}.getType());

		Page<RoomDTO> dtoPages = new PageImpl<>(dtos, pageable, rooms.getTotalElements());
		return new ResponseEntity<>(dtoPages, HttpStatus.OK);
	}
}
