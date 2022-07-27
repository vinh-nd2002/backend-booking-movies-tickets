package com.hust.services;

import java.util.List;

import com.hust.entity.Ticket;

public interface ITicketService {

	List<Ticket> getAllTickets();

	void acceptTicketById(int ticketId);

}
