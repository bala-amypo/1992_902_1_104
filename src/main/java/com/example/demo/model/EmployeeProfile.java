package com.example.demo.model;
import jakarta.persistence.*;
import java.time.localDateTime;

@Entity
public class EmployeeProfile{
    private Long id;
    private String employeeId;
    private String jobRole;
    private Boolean active;
    private LocalDateTime createdAt;
    
}