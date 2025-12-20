Model:====



=====






======





====




======





====






===

Controller:====

package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "employeeId"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;
    private String fullName;
    private String email;
    private String department;
    private String jobRole;
    private Boolean active;
    private LocalDateTime createdAt;

    // Default constructor
    public EmployeeProfile() {}

    // Parameterized constructor
    public EmployeeProfile(String employeeId, String fullName, String email,
                           String department, String jobRole, Boolean active) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.jobRole = jobRole;
        this.active = active;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}


=====
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "deviceCode"))
public class DeviceCatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceCode;
    private String deviceType;
    private String model;
    private Integer maxAllowedPerEmployee;
    private Boolean active;

    // Default constructor
    public DeviceCatalogItem() {}

    // Parameterized constructor
    public DeviceCatalogItem(String deviceCode, String deviceType, String model,
                             Integer maxAllowedPerEmployee, Boolean active) {
        this.deviceCode = deviceCode;
        this.deviceType = deviceType;
        this.model = model;
        this.maxAllowedPerEmployee = maxAllowedPerEmployee;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceCode() { return deviceCode; }
    public void setDeviceCode(String deviceCode) { this.deviceCode = deviceCode; }

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Integer getMaxAllowedPerEmployee() { return maxAllowedPerEmployee; }
    public void setMaxAllowedPerEmployee(Integer maxAllowedPerEmployee) {
        this.maxAllowedPerEmployee = maxAllowedPerEmployee;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}


=====


package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class IssuedDeviceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long deviceItemId;
    private LocalDate issuedDate;
    private LocalDate returnedDate;
    private String status;

    // Default constructor
    public IssuedDeviceRecord() {}

    // Parameterized constructor
    public IssuedDeviceRecord(Long employeeId, Long deviceItemId,
                              LocalDate issuedDate, String status) {
        this.employeeId = employeeId;
        this.deviceItemId = deviceItemId;
        this.issuedDate = issuedDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long getDeviceItemId() { return deviceItemId; }
    public void setDeviceItemId(Long deviceItemId) { this.deviceItemId = deviceItemId; }

    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    public LocalDate getReturnedDate() { return returnedDate; }
    public void setReturnedDate(LocalDate returnedDate) { this.returnedDate = returnedDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}


=====


package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode"))
public class PolicyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String description;
    private String appliesToRole;
    private String appliesToDepartment;
    private Integer maxDevicesAllowed;
    private Boolean active;

    // Default constructor
    public PolicyRule() {}

    // Parameterized constructor
    public PolicyRule(String ruleCode, String description,
                      String appliesToRole, String appliesToDepartment,
                      Integer maxDevicesAllowed, Boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.appliesToRole = appliesToRole;
        this.appliesToDepartment = appliesToDepartment;
        this.maxDevicesAllowed = maxDevicesAllowed;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAppliesToRole() { return appliesToRole; }
    public void setAppliesToRole(String appliesToRole) { this.appliesToRole = appliesToRole; }

    public String getAppliesToDepartment() { return appliesToDepartment; }
    public void setAppliesToDepartment(String appliesToDepartment) {
        this.appliesToDepartment = appliesToDepartment;
    }

    public Integer getMaxDevicesAllowed() { return maxDevicesAllowed; }
    public void setMaxDevicesAllowed(Integer maxDevicesAllowed) {
        this.maxDevicesAllowed = maxDevicesAllowed;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}


=====


package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long deviceItemId;
    private Boolean isEligible;
    private String reason;
    private LocalDateTime checkedAt;

    // Default constructor
    public EligibilityCheckRecord() {}

    // Parameterized constructor
    public EligibilityCheckRecord(Long employeeId, Long deviceItemId,
                                  Boolean isEligible, String reason) {
        this.employeeId = employeeId;
        this.deviceItemId = deviceItemId;
        this.isEligible = isEligible;
        this.reason = reason;
    }

    @PrePersist
    public void onCheck() {
        this.checkedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long getDeviceItemId() { return deviceItemId; }
    public void setDeviceItemId(Long deviceItemId) { this.deviceItemId = deviceItemId; }

    public Boolean getIsEligible() { return isEligible; }
    public void setIsEligible(Boolean isEligible) { this.isEligible = isEligible; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
}


======


package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String passwordHash;
    private String role;
    private Boolean active;

    // Default constructor
    public UserAccount() {}

    // Parameterized constructor
    public UserAccount(String fullName, String email,
                       String passwordHash, String role, Boolean active) {
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}




Repository:=====




====




===

package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    // Count active (ISSUED) devices for an employee
    @Query("SELECT COUNT(r) FROM IssuedDeviceRecord r " +
           "WHERE r.employeeId = :employeeId AND r.returnedDate IS NULL")
    long countActiveDevicesForEmployee(@Param("employeeId") Long employeeId);

    // Find active issued device for employee-device combination
    @Query("SELECT r FROM IssuedDeviceRecord r " +
           "WHERE r.employeeId = :employeeId " +
           "AND r.deviceItemId = :deviceItemId " +
           "AND r.returnedDate IS NULL")
    IssuedDeviceRecord findActiveByEmployeeAndDevice(
            @Param("employeeId") Long employeeId,
            @Param("deviceItemId") Long deviceItemId);

    // Get all issued devices by employee
    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);
}


=====


package com.example.demo.repository;

import com.example.demo.model.PolicyRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRuleRepository
        extends JpaRepository<PolicyRule, Long> {

    List<PolicyRule> findByActiveTrue();

    PolicyRule findByRuleCode(String ruleCode);
}


====
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

public interface UserAccountRepository
        extends JpaRepository<UserAccount, Long> {

    UserAccount findByEmail(String email);
}


Exception:=====




===




====




service::======





===




====





====




=====



======



impl:=====





====



====




====

====





===


======



spring.application.name=sql
#sql connectivity
spring.datasource.url=jdbc:mysql://localhost:3306/sample?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


#Hibernate Configration
spring.jpa.hibernate.ddl-auto=update
# spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect


======


dependency:===




<dependencies>

    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Jakarta Persistence -->
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
    </dependency>

    <!-- Jakarta Validation -->
    <dependency>
        <groupId>jakarta.validation</groupId>
        <artifactId>jakarta.validation-api</artifactId>
    </dependency>

    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.10.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Mockito -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <scope>test</scope>
    </dependency>

</dependencies>
