package org.cloud.tickets.services;

import org.apache.coyote.BadRequestException;
import org.cloud.tickets.dtos.LocationDto;
import org.cloud.tickets.dtos.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto createTicket(TicketDto ticketDto) throws BadRequestException;

    List<LocationDto> getAllLocations();

    LocationDto getLocationById(String id);
}
