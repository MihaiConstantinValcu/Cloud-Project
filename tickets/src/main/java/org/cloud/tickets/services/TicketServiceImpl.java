package org.cloud.tickets.services;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.cloud.tickets.dtos.LocationDto;
import org.cloud.tickets.dtos.MovieDto;
import org.cloud.tickets.dtos.TicketDto;
import org.cloud.tickets.entities.Ticket;
import org.cloud.tickets.feign.LocationsFeignService;
import org.cloud.tickets.repositories.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final LocationsFeignService locationsFeignService;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) throws BadRequestException {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        ticket.setId(UUID.randomUUID().toString());

        MovieDto movie = locationsFeignService.getMovieById(ticketDto.getMovieId());
        int totalSeats = movie.getSeats();
        int occupiedSeats = ticketRepository.findAllByDateAndMovieId(ticket.getDate(), ticket.getMovieId()).size();

        if (totalSeats <= occupiedSeats) {
            throw new BadRequestException("No seats available for this movie");
        }

        ticketRepository.save(ticket);
        TicketDto response = modelMapper.map(ticket, TicketDto.class);
        response.setMovieTitle(movie.getTitle());
        return response;
    }

    @Override
    public List<LocationDto> getAllLocations() {
        return locationsFeignService.getAllLocations().stream()
                .peek(location -> {
                    List<MovieDto> movies = location.getMovies().stream()
                            .filter(movie -> movie.getFromDate().isBefore(LocalDate.now())
                                    && movie.getToDate().isAfter(LocalDate.now()))
                            .toList();
                    location.setMovies(movies);
                })
                .filter(location -> !location.getMovies().isEmpty())
                .toList();
    }

    @Override
    public LocationDto getLocationById(String id) {
        LocationDto location = locationsFeignService.getLocationById(id);
        List<MovieDto> movies = location.getMovies().stream()
                .filter(movie -> movie.getToDate().isAfter(LocalDate.now()))
                .toList();
        location.setMovies(movies);

        return location;
    }
}
