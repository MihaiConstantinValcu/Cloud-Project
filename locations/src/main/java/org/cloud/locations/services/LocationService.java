package org.cloud.locations.services;

import org.cloud.locations.dtos.LocationDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAllLocations();

    LocationDto getById(String id);

    LocationDto createLocation(LocationDto locationDto);

    void deleteLocation(String id);
}
