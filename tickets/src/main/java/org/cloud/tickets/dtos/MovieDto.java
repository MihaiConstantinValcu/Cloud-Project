package org.cloud.tickets.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieDto {
    private String id;
    private String title;
    private String sourceId;
    private String locationId;
    private int seats;
    private LocalDate fromDate;
    private LocalDate toDate;
}
