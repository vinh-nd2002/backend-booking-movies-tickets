package com.hust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hust.entity.Ticket;

public interface ITicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {

	@Query("FROM Ticket t  WHERE t.ticketsOfUser.userId = :userId")
	List<Ticket> findTicketsByUserId(@Param("userId") int userId);

}
