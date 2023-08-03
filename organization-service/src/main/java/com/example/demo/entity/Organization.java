package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Organization {


    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
    private LocalDateTime createdDate;
}
