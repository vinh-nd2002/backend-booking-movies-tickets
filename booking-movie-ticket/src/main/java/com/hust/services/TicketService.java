package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.Ticket;
import com.hust.entity.Ticket.Status;
import com.hust.repository.ITicketRepository;

@Service
public class TicketService implements ITicketService {

	@Autowired
	private ITicketRepository ticketRepository;

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public void acceptTicketById(int ticketId) {
		// get ticket
		Ticket ticket = ticketRepository.getById(ticketId);

		// update status
		ticket.setTicketStatus(Status.ACCEPT);

		// save
		ticketRepository.save(ticket);
	}
}
