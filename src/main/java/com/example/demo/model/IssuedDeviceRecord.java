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
    private LocalDateTime issuedDate;
    private LocalDateTime returnedDate;
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
    public LocalDateTime getIssuedDate(){
        return issuedDate;

    }
    public void setIssuedDate(LocalDateTime issuedDate){
        this.issuedDate=issuedDate;
    }
    public LocalDateTime getReturnedDate(){
        return returnedDate;

    }
    public void setReturnedDate(LocalDateTime returnedDate){
        this.returnedDate=returnedDate;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public IssuedDeviceRecord(){

    }
    public IssuedDeviceRecord(Long id,Long employeeId,Long deviceItemId,LocalDateTime issuedDate,LocalDateTime returnedDate,String status){
        this.id=id;
        this.employeeId=employeeId;
        this.deviceItemId=deviceItemId;
        this.issuedDate=issuedDate;
        this.returnedDate=returnedDate;
        this.status=status;

    }
}