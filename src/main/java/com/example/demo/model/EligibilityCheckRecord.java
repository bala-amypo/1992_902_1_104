package com.example.demo.model;

import jakarta.persistence.*;
import java.Time.LocalDateTime;

#Entity
public class EligibilityCheckRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.Entity)
    private Long id;
    private Long employeeId;
    private Long deviceItemId;
    private Boolean isEligible;
    private String reason;
    private LocalDateTime checkedAt;

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
    public Boolean getIsEligible(){
        return isEligible;
    }
    public void setIsEligible(String isEligible){
        this.isEligible=isEligible;
    }
    public String getReason(){
        return reason;
    }
    public void setReason(String reason){
        this.reason=reason;
    }
    public 





}

