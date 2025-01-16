package org.cloud.locations.feign;

import org.cloud.locations.dtos.SourceMovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "movies", url = "http://movies:8080/api/movies")
public interface MoviesFeignService {
    @GetMapping
    List<SourceMovieDto> getAllMovies();
}
