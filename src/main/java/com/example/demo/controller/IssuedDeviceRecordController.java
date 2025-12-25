package com.example.demo.controller;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService service;

    public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
        this.service = service;
    }

    @PostMapping
    public IssuedDeviceRecord issueDevice(@RequestBody IssuedDeviceRecord record) {
        return service.issueDevice(record);
    }

    @PutMapping("/{id}/return")
    public IssuedDeviceRecord returnDevice(@PathVariable Long id) {
        return service.returnDevice(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(
            @PathVariable Long employeeId) {
        return service.getIssuedDevicesByEmployee(employeeId);
    }
}