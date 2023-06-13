package project.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.departmentservice.models.Department;
import project.departmentservice.models.Location;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class LocationDto {

    private Long id;

    private String street;

    private Integer houseNumber;

    private String city;

    private String country;

    private Long departmentId;

    public static LocationDto fromEntity(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .street(location.getStreet())
                .houseNumber(location.getHouseNumber())
                .city(location.getCity())
                .country(location.getCountry())
                .departmentId(location.getDepartment().getId())
                .build();
    }

    public static Location toEntity(LocationDto locationDto) {
        return Location.builder()
                .id(locationDto.getId())
                .street(locationDto.getStreet())
                .houseNumber(locationDto.getHouseNumber())
                .city(locationDto.getCity())
                .country(locationDto.getCountry())
                .department(
                        Department.builder()
                                .id(locationDto.getDepartmentId())
                                .build()
                ).build();
    }
}
