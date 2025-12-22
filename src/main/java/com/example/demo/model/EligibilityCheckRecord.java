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

    
    public EligibilityCheckRecord() {}

   
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
