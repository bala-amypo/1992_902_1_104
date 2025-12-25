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
=========
package com.example.demo.repository;

import com.example.demo.model.DeviceCatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceCatalogItemRepository
        extends JpaRepository<DeviceCatalogItem, Long> {

    DeviceCatalogItem findByDeviceCode(String deviceCode);
}
==========

package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    long countActiveDevicesForEmployee(Long employeeId);

    IssuedDeviceRecord findActiveByEmployeeAndDevice(
            Long employeeId,
            Long deviceItemId
    );

    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);
}
=========

package com.example.demo.repository;

import com.example.demo.model.PolicyRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRuleRepository
        extends JpaRepository<PolicyRule, Long> {

    List<PolicyRule> findByActiveTrue();

    PolicyRule findByRuleCode(String ruleCode);
}
=========

package com.example.demo.repository;

import com.example.demo.model.EligibilityCheckRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EligibilityCheckRecordRepository
        extends JpaRepository<EligibilityCheckRecord, Long> {

    List<EligibilityCheckRecord> findByEmployeeId(Long employeeId);
}
=======

package com.example.demo.repository;

import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository
        extends JpaRepository<UserAccount, Long> {

    UserAccount findByEmail(String email);
}
