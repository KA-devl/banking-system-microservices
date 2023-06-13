package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081/", name = "department-service")
public interface APIClient {

    @GetMapping("api/departments/{departmentCode}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode (@PathVariable String departmentCode);
}
