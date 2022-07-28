package com.hust.services;

import java.util.List;

import com.hust.entity.Ticket;
import com.hust.form.filter.TicketFilterForm;

public interface ITicketService {

	List<Ticket> getAllTickets(String search, TicketFilterForm filterForm);

	void acceptTicketById(int ticketId);

}
