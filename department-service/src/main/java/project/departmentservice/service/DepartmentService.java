package project.departmentservice.service;

import project.departmentservice.dto.DepartmentDto;
import project.departmentservice.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String departmentCode);

    List<DepartmentDto> getAllDepartments();

    void deleteDepartmentById(Long departmentId);
}
