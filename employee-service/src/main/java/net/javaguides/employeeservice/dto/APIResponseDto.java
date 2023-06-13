package net.javaguides.employeeservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponseDto {
    private EmployeeDto employee;
    private DepartmentDto department;
}
