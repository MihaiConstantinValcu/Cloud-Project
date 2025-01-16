package org.cloud.locations.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SourceMovieDto {
    private String id;
    private String name;
    private String description;
    private String rating;
    private LocalDate publishDate;
}
