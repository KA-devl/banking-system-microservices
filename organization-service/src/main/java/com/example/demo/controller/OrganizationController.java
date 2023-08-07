package com.example.demo.controller;

import com.example.demo.dto.OrganizationDto;
import com.example.demo.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/{organization-code}")
    public ResponseEntity<OrganizationDto> findByOrganizationCode(@PathVariable("organization-code") String organizationCode) {
        return ResponseEntity.ok(organizationService.findByOrganizationCode(organizationCode));
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> save(@RequestBody OrganizationDto organizationDto){
        return ResponseEntity.ok(organizationService.save(organizationDto));
    }
}
