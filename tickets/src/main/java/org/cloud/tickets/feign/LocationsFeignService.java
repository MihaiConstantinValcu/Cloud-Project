package org.cloud.tickets.feign;

import org.cloud.tickets.dtos.LocationDto;
import org.cloud.tickets.dtos.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "locations", url = "http://locations:8080/api")
public interface LocationsFeignService {
    @GetMapping("/locations")
    List<LocationDto> getAllLocations();

    @GetMapping("/locations/{id}")
    LocationDto getLocationById(@PathVariable("id") String id);

    @GetMapping("/movies/{id}")
    MovieDto getMovieById(@PathVariable("id") String id);

    @GetMapping("/movies")
    List<MovieDto> getAllMovies();
}
