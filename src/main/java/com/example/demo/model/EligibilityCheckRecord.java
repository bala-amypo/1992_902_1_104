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
    pblic void setI(Long ){
        this.
    }
    public Long get(){
        return 
    }
    pblic void set(Long ){
        this.
    }
    public Long get(){
        return 
    }
    pblic void set(Long ){
        this.
    }





}

