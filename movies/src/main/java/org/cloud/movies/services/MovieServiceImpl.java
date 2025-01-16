package org.cloud.movies.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cloud.movies.dtos.MovieDto;
import org.cloud.movies.entities.Movie;
import org.cloud.movies.repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MovieDto> findAll() {
        return movieRepository.findAll().stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .toList();
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        movie.setId(UUID.randomUUID().toString());

        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto, String id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        movieDto.setId(id);
        modelMapper.map(movieDto, movie);

        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }
}
