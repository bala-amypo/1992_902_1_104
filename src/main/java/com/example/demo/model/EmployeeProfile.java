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

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getEmployeeId(){
        return employeeId;
    }
    
}