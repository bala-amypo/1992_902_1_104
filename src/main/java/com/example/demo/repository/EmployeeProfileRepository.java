package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    EmployeeProfile findByEmployeeId(String employeeId);

    EmployeeProfile findByEmail(String email);
}
