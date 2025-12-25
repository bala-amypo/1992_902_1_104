package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    @Query("""
           SELECT COUNT(r)
           FROM IssuedDeviceRecord r
           WHERE r.employeeId = :employeeId
             AND r.status = 'ISSUED'
           """)
    long countActiveDevicesForEmployee(@Param("employeeId") Long employeeId);

    @Query("""
           SELECT r
           FROM IssuedDeviceRecord r
           WHERE r.employeeId = :employeeId
             AND r.deviceItemId = :deviceItemId
             AND r.status = 'ISSUED'
           """)
    IssuedDeviceRecord findActiveByEmployeeAndDevice(
            @Param("employeeId") Long employeeId,
            @Param("deviceItemId") Long deviceItemId
    );

    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);
}
