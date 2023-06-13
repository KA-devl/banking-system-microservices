package project.departmentservice.service;

import project.departmentservice.dto.LocationDetails;
import project.departmentservice.dto.LocationDto;

import java.util.List;

public interface LocationService {

    LocationDto saveLocation(LocationDto locationDto);

    LocationDto updateLocation(LocationDto locationDto);

    List<LocationDetails> getAllLocationsByDepartmentId(Long departmentId);


    void deleteLocationById(Long locationId);
}
