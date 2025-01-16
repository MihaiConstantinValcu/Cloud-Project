package org.cloud.tickets.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TicketDto {
    private String id;
    private String movieTitle;
    private String movieId;
    private LocalDate date;
}
