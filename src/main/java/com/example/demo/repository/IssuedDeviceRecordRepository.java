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
