package org.cloud.movies.services;

import org.cloud.movies.dtos.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> findAll();

    MovieDto createMovie(MovieDto movieDto);

    MovieDto updateMovie(MovieDto movieDto, String id);
}
