package com.example.demo.dto;

import com.example.demo.model.Organization;
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

    private static Organization toEntity(OrganizationDto organizationDto) {
        return Organization.builder()
                .organizationName(organizationDto.organizationName)
                .organizationCode(organizationDto.organizationCode)
                .organizationDescription(organizationDto.organizationDescription)
                .build();
    }

    private static OrganizationDto fromEntity(Organization organization) {
        return OrganizationDto.builder()
                .organizationName(organization.getOrganizationName())
                .organizationCode(organization.getOrganizationCode())
                .organizationDescription(organization.getOrganizationDescription())
                .createdAt(organization.getCreatedAt())
                .build();
    }
}
