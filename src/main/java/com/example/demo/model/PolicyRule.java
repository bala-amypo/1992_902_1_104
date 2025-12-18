package com.example.demo.model;
import jakarta.persistence.*;

@Entity

public class PolicyRule{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String ruleCode;
    private String description;
    private String appliesToRole;
    private String appliesToDepartment;
    private Integer maxDevicesAllowed;
    private Boolean active;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getRuleCode(){
        return ruleCode;

    }
    public void set(){

    }
     public String get(){

    }
    public void set(){
        
    }
     public String get(){

    }
    public void set(){
        
    }
     public String get(){

    }
    public void set(){
        
    }
    
}