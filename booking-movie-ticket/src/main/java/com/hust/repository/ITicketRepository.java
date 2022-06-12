package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hust.entity.Ticket;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

}
