package com.example.demo.model;
import jakarta.persistence.*;

@Entity
public class UserAccount{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String passwordHash;
    private String role;
    private Boolean active;
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String get(){
        return 
    }
    public void set(String ){
        this.
    }
    
}