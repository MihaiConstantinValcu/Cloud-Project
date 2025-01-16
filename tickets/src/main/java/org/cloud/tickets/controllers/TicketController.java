package org.cloud.tickets.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.cloud.tickets.dtos.LocationDto;
import org.cloud.tickets.dtos.TicketDto;
import org.cloud.tickets.services.TicketService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
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
    public ResponseEntity<Resource> createTicket(@RequestBody TicketDto ticketDto) throws BadRequestException {
        TicketDto ticket = ticketService.createTicket(ticketDto);

        String fileContent = "This is your ticket ID, please present it at the cinema: " + ticket.getId();
        byte[] fileBytes = fileContent.getBytes(StandardCharsets.UTF_8);
        ByteArrayResource resource = new ByteArrayResource(fileBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket.txt");
        headers.setContentType(MediaType.TEXT_PLAIN);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileBytes.length)
                .body(resource);
    }
}
