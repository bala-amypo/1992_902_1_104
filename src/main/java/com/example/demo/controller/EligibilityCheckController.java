package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService service;

    public EligibilityCheckController(EligibilityCheckService service) {
        this.service = service;
    }

    // Validate eligibility
    @PostMapping("/validate/{employeeId}/{deviceItemId}")
    public EligibilityCheckRecord validateEligibility(@PathVariable Long employeeId,
                                                      @PathVariable Long deviceItemId) {
        return service.validateEligibility(employeeId, deviceItemId);
    }

    // Get checks by employee
    @GetMapping("/employee/{employeeId}")
    public List<EligibilityCheckRecord> getChecks(@PathVariable Long employeeId) {
        return service.getChecksByEmployee(employeeId);
    }
}
