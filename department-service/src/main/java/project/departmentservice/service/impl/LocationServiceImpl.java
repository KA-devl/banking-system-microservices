package project.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.departmentservice.dto.LocationDetails;
import project.departmentservice.dto.LocationDto;
import project.departmentservice.models.Location;
import project.departmentservice.repository.LocationRepository;
import project.departmentservice.service.LocationService;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public LocationDto saveLocation(LocationDto locationDto) {
        return LocationDto.fromEntity(locationRepository.save(LocationDto.toEntity(locationDto)));
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto) {
        Location location = locationRepository.findById(locationDto.getId())
                .orElseThrow(()-> new EntityNotFoundException("Location with id" +locationDto.getId() +"was not found"));
        location.setCity(locationDto.getCity());
        location.setStreet(locationDto.getStreet());
        location.setCountry(locationDto.getCountry());
        location.setHouseNumber(locationDto.getHouseNumber());

        return LocationDto.fromEntity(locationRepository.save(location));
    }

    @Override
    public List<LocationDetails> getAllLocationsByDepartmentId(Long departmentId) {
       return locationRepository.findAllLocationsByDepartmentId(departmentId);
    }


    @Override
    public void deleteLocationById(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID: " + locationId));
        locationRepository.deleteById(locationId);
    }

}
