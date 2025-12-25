package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {

    // 🔹 Used to check duplicate active issuance
    @Query("""
           SELECT r FROM IssuedDeviceRecord r
           WHERE r.employeeId = :employeeId
             AND r.deviceItemId = :deviceItemId
             AND r.status = 'ISSUED'
           """)
    List<IssuedDeviceRecord> findActiveByEmployeeAndDevice(Long employeeId, Long deviceItemId);

    // 🔹 Used to enforce max devices per employee
    @Query("""
           SELECT COUNT(r) FROM IssuedDeviceRecord r
           WHERE r.employeeId = :employeeId
             AND r.status = 'ISSUED'
           """)
    Long countActiveDevicesForEmployee(Long employeeId);

    // 🔹 Used to fetch issued devices per employee
    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);
}