package project.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.departmentservice.models.Department;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DepartmentDto {

    private Long id;

    private String departmentName;

    private String departmentDescription;

    private String departmentCode;

    public static DepartmentDto fromEntity(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentDescription(department.getDepartmentDescription())
                .departmentCode(department.getDepartmentCode())
                .build();

    }

    public static Department toEntity(DepartmentDto departmentDto) {
        return Department.builder()
                .id(departmentDto.getId())
                .departmentName(departmentDto.getDepartmentName())
                .departmentDescription(departmentDto.getDepartmentDescription())
                .departmentCode(departmentDto.getDepartmentCode())
                .build();
    }
}
