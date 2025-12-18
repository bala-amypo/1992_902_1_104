package com.example.demo.model;
import jakarta.persistence.*;

@Entity
public class DeviceCatalogItem{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String deviceCode;
    private String deviceType;
    private String model;
    private Integer maxAllowedEmployee;
    private Boolean active;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getDeviceCode(){
        return deviceCode;
    }
    public void setDeviceCode(String deviceCode){
        this.deviceCode=deviceCode;
    }
    public String getDeviceType(){
        return deviceType;
    }  
    public void setDeviceType(String deviceType){
        this.deviceType=deviceType;
    }
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model=model;
    }
    public Integer getMaxAllowedEmployee(){
        return maxAllowedEmployee;
    }
    public void setMaxAllowedEmployee(Integer maxAllowedEmployee){
        this.maxAllowedEmployee=maxAllowedEmployee;

    }
    public Boolean getActive(){
        return active;
    }
    public void setActive(Boolean active){
        this.active=active;
    }
    public DeviceCatalogItem(){

    }
    public DeviceCatalogItem(Long id,String deviceCode,String deviceType,String model,Integer maxAllowedEmployee,Boolean active){
        this.id=id;
        this.deviceCode=deviceCode;
        this.deviceType=deviceType;
        this.model=model;
        this.maxAllowedEmployee=maxAllowedEmployee;
        this.active=active;
        
    }
    


}