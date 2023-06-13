package project.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.departmentservice.dto.LocationDetails;
import project.departmentservice.models.Location;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

        @Query("SELECT l.city as city, l.country as country, l.houseNumber as houseNumber, " +
                "l.street as street FROM Location l WHERE l.department.id = :departmentId")
        List<LocationDetails> findAllLocationsByDepartmentId(Long departmentId);
}
