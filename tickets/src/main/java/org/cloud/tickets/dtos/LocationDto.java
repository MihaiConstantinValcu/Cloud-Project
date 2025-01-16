package org.cloud.tickets.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationDto {
    private String id;
    private String locationName;
    private String locationAddress;
    private List<MovieDto> movies;
}
