package org.cloud.tickets.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.cloud.tickets.dtos.LocationDto;
import org.cloud.tickets.dtos.TicketDto;
import org.cloud.tickets.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/locations")
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        return ResponseEntity.ok(ticketService.getAllLocations());
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable String id) {
        return ResponseEntity.ok(ticketService.getLocationById(id));
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) throws BadRequestException {
        return ResponseEntity.ok(ticketService.createTicket(ticketDto));
    }
}
