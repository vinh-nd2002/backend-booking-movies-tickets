package com.hust.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hust.dto.TicketDTO;
import com.hust.entity.Ticket;
import com.hust.form.filter.TicketFilterForm;
import com.hust.services.ITicketService;

@RestController
@RequestMapping(value = "api/v1/tickets")
@CrossOrigin("*")
public class TicketController {

	@Autowired
	private ITicketService ticketService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public ResponseEntity<?> getAllTickets(@RequestParam(value = "search", required = false) String search,
			TicketFilterForm filterForm) {
		// get all entity
		List<Ticket> tickets = ticketService.getAllTickets(search, filterForm);

		// convert to dtos
		List<TicketDTO> dtos = modelMapper.map(tickets, new TypeToken<List<TicketDTO>>() {
		}.getType());
		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> acceptTicketById(@PathVariable(name = "id") int ticketId) {
		ticketService.acceptTicketById(ticketId);
		return new ResponseEntity<>("Update success", HttpStatus.OK);
	}
}
