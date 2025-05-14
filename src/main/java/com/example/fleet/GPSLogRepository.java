package com.example.fleet;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface GPSLogRepository extends JpaRepository<GPSLog, Long> {
    Optional<GPSLog> findTopByVehicleIdOrderByTimestampDesc(Long vehicleId);
    List<GPSLog> findByVehicleIdAndTimestampBetween(Long vehicleId, LocalDateTime from, LocalDateTime to);

    @Transactional
    @Modifying
    int deleteByTimestampBefore(LocalDateTime threshold);
}