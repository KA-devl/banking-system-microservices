package net.javaguides.employeeservice.service.impl;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.dto.OrganizationDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.DepartmentClient;
import net.javaguides.employeeservice.service.EmployeeService;
import net.javaguides.employeeservice.service.OrganizationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

//    private WebClient webClient;

    private DepartmentClient departmentClient;

    private OrganizationClient organizationClient;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee saveDEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(saveDEmployee);

        return savedEmployeeDto;
    }

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment") //circuit breaker pattern
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment") //retry pattern
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();

//       DepartmentDto departmentDto = webClient.get().uri("http://localhost:8081/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        if(employee.getDepartmentCode() == null){
            throw new RuntimeException("Department Code is null");
        }
        DepartmentDto departmentDto = departmentClient.getDepartmentByCode(employee.getDepartmentCode()).getBody();
        OrganizationDto organizationDto = organizationClient.getOrganizationByCode(employee.getOrganizationCode()).getBody();
        return APIResponseDto.builder()
                .employee(EmployeeMapper.mapToEmployeeDto(employee))
                .department(departmentDto)
                .organization(organizationDto)
                .build();
    }
    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
        LOGGER.info("Inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("H1S2ZX");
        departmentDto.setDepartmentName("Fallback department");
        departmentDto.setDepartmentDescription("This is a fallback department as a response to when department service is down");

        return APIResponseDto.builder()
                .department(departmentDto)
                .employee(EmployeeMapper.mapToEmployeeDto(employee))
                .build();
    }

//    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
//
//        LOGGER.info("inside getDefaultDepartment() method");
//
//        Employee employee = employeeRepository.findById(employeeId).get();
//
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setDepartmentName("R&D Department");
//        departmentDto.setDepartmentCode("RD001");
//        departmentDto.setDepartmentDescription("Research and Development Department");
//
//        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
//
//        APIResponseDto apiResponseDto = new APIResponseDto();
//        apiResponseDto.setEmployee(employeeDto);
//        apiResponseDto.setDepartment(departmentDto);
//        return apiResponseDto;
//    }
}
