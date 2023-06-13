package project.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.departmentservice.dto.DepartmentDto;
import project.departmentservice.models.Department;
import project.departmentservice.repository.DepartmentRepository;
import project.departmentservice.service.DepartmentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentDto.toEntity(departmentDto);
        departmentRepository.save(department);

        return DepartmentDto.fromEntity(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode())
                .orElseThrow(()-> new EntityNotFoundException("Department not found"));
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        //department.setDepartmentName(departmentDto.getDepartmentName());
        departmentRepository.save(department);
        return DepartmentDto.fromEntity(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(()-> new EntityNotFoundException("Department not found"));
        return DepartmentDto.fromEntity(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream().map(department -> DepartmentDto.fromEntity(department))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + departmentId));
        departmentRepository.deleteById(departmentId);
    }
}
