package org.cloud.movies.controllers;

import lombok.RequiredArgsConstructor;
import org.cloud.movies.dtos.MovieDto;
import org.cloud.movies.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.createMovie(movieDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto,
                                                @PathVariable String id){
        return ResponseEntity.ok(movieService.updateMovie(movieDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
