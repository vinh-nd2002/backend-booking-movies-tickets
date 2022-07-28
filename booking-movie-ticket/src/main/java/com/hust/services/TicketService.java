package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.entity.Ticket;
import com.hust.entity.Ticket.Status;
import com.hust.form.filter.TicketFilterForm;
import com.hust.repository.ITicketRepository;
import com.hust.spectification.ticket.TicketSpectificationBuilder;

@Service
public class TicketService implements ITicketService {

	@Autowired
	private ITicketRepository ticketRepository;

	@Override
	public List<Ticket> getAllTickets(String search, TicketFilterForm filterForm) {
		TicketSpectificationBuilder builder = new TicketSpectificationBuilder(filterForm, search);
		return ticketRepository.findAll(builder.build());
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
