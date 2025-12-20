package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;
    private final IssuedDeviceRecordRepository issuedRepo;
    private final PolicyRuleRepository policyRepo;
    private final EligibilityCheckRecordRepository checkRepo;

    public EligibilityCheckServiceImpl(
            EmployeeProfileRepository employeeRepo,
            DeviceCatalogItemRepository deviceRepo,
            IssuedDeviceRecordRepository issuedRepo,
            PolicyRuleRepository policyRepo,
            EligibilityCheckRecordRepository checkRepo) {

        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
        this.issuedRepo = issuedRepo;
        this.policyRepo = policyRepo;
        this.checkRepo = checkRepo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {

        EmployeeProfile employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (!employee.getActive()) {
            throw new BadRequestException("Employee not active");
        }

        DeviceCatalogItem device = deviceRepo.findById(deviceItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));

        if (!device.getActive()) {
            throw new BadRequestException("Device inactive");
        }

        if (issuedRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId) != null) {
            throw new BadRequestException("active issuance exists");
        }

        long count = issuedRepo.countActiveDevicesForEmployee(employeeId);
        if (count >= device.getMaxAllowedPerEmployee()) {
            throw new BadRequestException("Maximum allowed devices");
        }

        for (PolicyRule rule : policyRepo.findByActiveTrue()) {
            if (rule.getMaxDevicesAllowed() != null &&
                count >= rule.getMaxDevicesAllowed()) {
                throw new BadRequestException("Policy violation");
            }
        }

        EligibilityCheckRecord record =
                new EligibilityCheckRecord(employeeId, deviceItemId, true, "Eligible");

        return checkRepo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return checkRepo.findByEmployeeId(employeeId);
    }
}

