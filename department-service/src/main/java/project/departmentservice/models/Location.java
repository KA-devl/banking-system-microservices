package project.departmentservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
@SuperBuilder
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    private String street;

    private Integer houseNumber;

    private String city;

    private String country;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
