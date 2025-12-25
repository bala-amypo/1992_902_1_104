package com.example.demo.controller;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService issuedService;

    public IssuedDeviceRecordController(IssuedDeviceRecordService issuedService) {
        this.issuedService = issuedService;
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        return ResponseEntity.ok(issuedService.returnDevice(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<IssuedDeviceRecord>> getByEmployee(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(issuedService.getIssuedDevicesByEmployee(employeeId));
    }
}

