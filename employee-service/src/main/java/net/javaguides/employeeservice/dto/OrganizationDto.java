package net.javaguides.employeeservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrganizationDto {

    private String organizationName;
    private String organizationCode;
    private String organizationDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
