package org.cloud.tickets.repositories;

import org.cloud.tickets.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findAllByDateAndMovieId(LocalDate date, String movieId);
}
