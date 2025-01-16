package org.cloud.locations.services;

import lombok.RequiredArgsConstructor;
import org.cloud.locations.dtos.MovieDto;
import org.cloud.locations.dtos.SourceMovieDto;
import org.cloud.locations.entities.Movie;
import org.cloud.locations.feign.MoviesFeignService;
import org.cloud.locations.repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final MoviesFeignService moviesFeignService;

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .toList();
    }

    @Override
    public MovieDto findById(String id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        movie.setId(UUID.randomUUID().toString());

        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public List<SourceMovieDto> getSourceMovies() {
        return moviesFeignService.getAllMovies();
    }
}
