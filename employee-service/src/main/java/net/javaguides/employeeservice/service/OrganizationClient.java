package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationClient {
    @GetMapping("api/organizations/{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode (@PathVariable String organizationCode);
}
