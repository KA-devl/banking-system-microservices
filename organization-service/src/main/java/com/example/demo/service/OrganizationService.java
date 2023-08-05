package com.example.demo.service;

import com.example.demo.dto.OrganizationDto;

public interface OrganizationService {

    OrganizationDto findByOrganizationCode(String organizationCode);
}
