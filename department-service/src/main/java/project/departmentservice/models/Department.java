package project.departmentservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
@SuperBuilder
public class Department {

    @Id
    @GeneratedValue
    private Long id;

    private String departmentName;

    private String departmentDescription;

    private String departmentCode;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Location> locations;
}
