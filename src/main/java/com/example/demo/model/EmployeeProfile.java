package com.example.demo.model;
import jakarta.persistence.*;
import java.time.localDateTime;

@Entity
public class EmployeeProfile{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private String jobRole;
    private Boolean active;
    private LocalDateTime createdAt;
    
}