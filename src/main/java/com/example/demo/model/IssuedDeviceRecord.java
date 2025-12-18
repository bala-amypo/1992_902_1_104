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
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getEmployeeId(){
        return employeeId;
    }
    public void setEmployeeId(Long employeeId){
        this.employeeId=employeeId;
    }
    public Long getDeviceItemId(){
        return deviceItemId;
    }
    public void setDeviceItemId(Long deviceItemId){
        this.deviceItemId=deviceItemId;
    }
    public LocalDate
}