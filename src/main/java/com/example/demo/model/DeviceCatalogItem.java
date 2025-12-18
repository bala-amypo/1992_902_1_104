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
    public void set(Long id){
        this.id=id;
    }
    public 


}