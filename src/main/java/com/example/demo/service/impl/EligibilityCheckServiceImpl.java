package com.example.demo.service.impl;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.repository.EligibilityCheckRecordRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final IssuedDeviceRecordRepository issuedRepo;
    private final EligibilityCheckRecordRepository eligibilityRepo;

    public EligibilityCheckServiceImpl(
            IssuedDeviceRecordRepository issuedRepo,
            EligibilityCheckRecordRepository eligibilityRepo) {
        this.issuedRepo = issuedRepo;
        this.eligibilityRepo = eligibilityRepo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {

        long count = issuedRepo.countActiveDevicesForEmployee(employeeId);

        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);

        if (count > 0) {
            record.setIsEligible(false);
            record.setReason("Device limit exceeded");
        } else {
            record.setIsEligible(true);
            record.setReason("Eligible");
        }

        return eligibilityRepo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityRepo.findByEmployeeId(employeeId);
    }
}
