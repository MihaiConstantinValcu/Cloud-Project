package org.cloud.locations.services;

import lombok.RequiredArgsConstructor;
import org.cloud.locations.dtos.LocationDto;
import org.cloud.locations.dtos.MovieDto;
import org.cloud.locations.entities.Location;
import org.cloud.locations.repositories.LocationRepository;
import org.cloud.locations.repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(location -> {
                    LocationDto dto = modelMapper.map(location, LocationDto.class);
                    dto.setMovies(movieRepository.findAllByLocationId(location.getId()).stream()
                            .map(movie -> modelMapper.map(movie, MovieDto.class))
                            .toList());
                    return dto;
                })
                .toList();
    }

    @Override
    public LocationDto getById(String id) {
        Location location = locationRepository.findById(id).orElseThrow();
        LocationDto dto = modelMapper.map(location, LocationDto.class);
        dto.setMovies(movieRepository.findAllByLocationId(location.getId()).stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .toList());
        return dto;
    }

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        Location location = modelMapper.map(locationDto, Location.class);
        location.setId(UUID.randomUUID().toString());

        locationRepository.save(location);
        return modelMapper.map(location, LocationDto.class);
    }
}
