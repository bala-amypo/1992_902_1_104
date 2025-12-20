package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    // Create employee
    @PostMapping
    public EmployeeProfile createEmployee(@RequestBody EmployeeProfile employee) {
        return service.createEmployee(employee);
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public EmployeeProfile getEmployee(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    // Get all employees
    @GetMapping
    public List<EmployeeProfile> getAllEmployees() {
        return service.getAllEmployees();
    }

    // Activate / deactivate employee
    @PutMapping("/{id}/status")
    public EmployeeProfile updateStatus(@PathVariable Long id,
                                        @RequestParam boolean active) {
        return service.updateEmployeeStatus(id, active);
    }
}

