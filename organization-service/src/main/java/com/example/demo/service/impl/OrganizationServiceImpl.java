package com.example.demo.service.impl;

import com.example.demo.dto.OrganizationDto;
import com.example.demo.model.Organization;
import com.example.demo.repository.OrganizationRepository;
import com.example.demo.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto findByOrganizationCode(String organizationCode) {
        //map organization entity to organization dto
        return OrganizationDto.fromEntity(organizationRepository.findByOrganizationCode(organizationCode));
    }
    @Override
    public OrganizationDto save(OrganizationDto organizationDto) {
        Organization organization = OrganizationDto.toEntity(organizationDto);
        return OrganizationDto.fromEntity(organizationRepository.save(organization));
    }
}