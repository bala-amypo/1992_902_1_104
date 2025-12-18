package com.example.demo.model.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class IssuedDeviceRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private Long deviceItemId;
    private LocalDate issuedDate;
    private LocalDate returnedDate;
    private String status;
    
}