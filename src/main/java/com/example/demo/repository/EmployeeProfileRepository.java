package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {

    Optional<EmployeeProfile> findByEmployeeId(String employeeId);

    Optional<EmployeeProfile> findByEmail(String email);
}


========


===========




=========

package com.example.demo.repository;

import com.example.demo.model.PolicyRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyRuleRepository extends JpaRepository<PolicyRule, Long> {

    Optional<PolicyRule> findByRuleCode(String ruleCode);

    List<PolicyRule> findByActiveTrue();
}
=========



package com.example.demo.repository;

import com.example.demo.model.EligibilityCheckRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EligibilityCheckRecordRepository
        extends JpaRepository<EligibilityCheckRecord, Long> {

    List<EligibilityCheckRecord> findByEmployeeId(Long employeeId);
}

====

package com.example.demo.repository;

import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByEmail(String email);
}

