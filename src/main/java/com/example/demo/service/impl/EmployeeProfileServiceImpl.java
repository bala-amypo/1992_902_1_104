package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeRepo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employee) {
        if (employeeRepo.findByEmployeeId(employee.getEmployeeId()).isPresent()) {
            throw new BadRequestException("EmployeeId already exists");
        }
        if (employeeRepo.findByEmail(employee.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        return employeeRepo.save(employee);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeProfile> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
        EmployeeProfile employee = getEmployeeById(id);
        employee.setActive(active);
        return employeeRepo.save(employee);
    }
}


========



========

package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository issuedRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository issuedRepo,
                                         EmployeeProfileRepository employeeRepo,
                                         DeviceCatalogItemRepository deviceRepo) {
        this.issuedRepo = issuedRepo;
        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {
        IssuedDeviceRecord record = issuedRepo.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        if ("RETURNED".equals(record.getStatus())) {
            throw new BadRequestException("already returned");
        }

        record.setReturnedDate(LocalDate.now());
        record.setStatus("RETURNED");
        return issuedRepo.save(record);
    }

    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return issuedRepo.findByEmployeeId(employeeId);
    }
}



==========



package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PolicyRule;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.PolicyRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {

    private final PolicyRuleRepository policyRepo;

    public PolicyRuleServiceImpl(PolicyRuleRepository policyRepo) {
        this.policyRepo = policyRepo;
    }

    @Override
    public PolicyRule createRule(PolicyRule rule) {
        if (policyRepo.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new BadRequestException("Rule code");
        }
        return policyRepo.save(rule);
    }

    @Override
    public List<PolicyRule> getAllRules() {
        return policyRepo.findAll();
    }

    @Override
    public List<PolicyRule> getActiveRules() {
        return policyRepo.findByActiveTrue();
    }
}



==========


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



======


package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountRepository userRepo,
                                  PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount register(UserAccount user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepo.save(user);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
