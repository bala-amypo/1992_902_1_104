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