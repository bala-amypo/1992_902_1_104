package com.example.demo.model;

import jakarta.persistence.*;
import java.Time.LocalDateTime;

#Entity
public class EligibilityCheckRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.Entity)
    private Long id;
    private Long employeeId




}