package org.cloud.locations.services;

import org.cloud.locations.dtos.MovieDto;
import org.cloud.locations.dtos.SourceMovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> getAllMovies();

    MovieDto findById(String id);

    MovieDto createMovie(MovieDto movieDto);

    List<SourceMovieDto> getSourceMovies();
}
