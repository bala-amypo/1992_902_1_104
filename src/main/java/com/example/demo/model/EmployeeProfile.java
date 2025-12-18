package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

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
    public void setEmployeeId(String employeeId){
        this.employeeId=employeeId;
    }
    public String getJobRole(){
        return jobRole;
    }
    public void setJobRole(String jobRole){
        this.jobRole=jobRole;
    }
    public Boolean getActive(){
        return active;
    }
    public void setActive(Boolean active){
        this.active=active;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public EmployeeProfile(){
        
    }
    public EmployeeProfile(Long id,String employeeId,String jobRole,Boolean active,LocalDateTime createdAt){
        this.id=id;
        this.employeeId=employeeId;
        this.jobRole=jobRole;
        this.active=active;
        this.createdAt=createdAt;

    }
}