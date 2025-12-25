package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;
    private final IssuedDeviceRecordRepository issuedRepo;
    private final PolicyRuleRepository policyRepo;
    private final EligibilityCheckRecordRepository eligibilityRepo;

    public EligibilityCheckServiceImpl(EmployeeProfileRepository employeeRepo,
                                       DeviceCatalogItemRepository deviceRepo,
                                       IssuedDeviceRecordRepository issuedRepo,
                                       PolicyRuleRepository policyRepo,
                                       EligibilityCheckRecordRepository eligibilityRepo) {
        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
        this.issuedRepo = issuedRepo;
        this.policyRepo = policyRepo;
        this.eligibilityRepo = eligibilityRepo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {

        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);

        Optional<EmployeeProfile> employeeOpt = employeeRepo.findById(employeeId);
        Optional<DeviceCatalogItem> deviceOpt = deviceRepo.findById(deviceItemId);

        // ❌ Employee or Device not found
        if (employeeOpt.isEmpty() || deviceOpt.isEmpty()) {
            record.setIsEligible(false);
            record.setReason("not found");
            return eligibilityRepo.save(record);
        }

        EmployeeProfile employee = employeeOpt.get();
        DeviceCatalogItem device = deviceOpt.get();

        // ❌ Employee inactive
        if (!Boolean.TRUE.equals(employee.getActive())) {
            record.setIsEligible(false);
            record.setReason("not active");
            return eligibilityRepo.save(record);
        }

        // ❌ Device inactive
        if (!Boolean.TRUE.equals(device.getActive())) {
            record.setIsEligible(false);
            record.setReason("inactive");
            return eligibilityRepo.save(record);
        }

        // ❌ Active issuance already exists
        if (!issuedRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId).isEmpty()) {
            record.setIsEligible(false);
            record.setReason("active issuance");
            return eligibilityRepo.save(record);
        }

        // ❌ Max allowed devices reached
        Long activeCount = issuedRepo.countActiveDevicesForEmployee(employeeId);
        if (activeCount != null && activeCount >= device.getMaxAllowedPerEmployee()) {
            record.setIsEligible(false);
            record.setReason("Maximum allowed devices");
            return eligibilityRepo.save(record);
        }

        // ❌ Policy rule violations
        List<PolicyRule> rules = policyRepo.findByActiveTrue();
        for (PolicyRule rule : rules) {
            boolean roleMatch = rule.getAppliesToRole() == null ||
                    rule.getAppliesToRole().equals(employee.getJobRole());

            boolean deptMatch = rule.getAppliesToDepartment() == null ||
                    rule.getAppliesToDepartment().equals(employee.getDepartment());

            if (roleMatch && deptMatch) {
                if (rule.getMaxDevicesAllowed() != null &&
                        activeCount >= rule.getMaxDevicesAllowed()) {
                    record.setIsEligible(false);
                    record.setReason("Policy violation");
                    return eligibilityRepo.save(record);
                }
            }
        }

        // ✅ Eligible
        record.setIsEligible(true);
        record.setReason("Eligible");
        return eligibilityRepo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityRepo.findByEmployeeId(employeeId);
    }
}

