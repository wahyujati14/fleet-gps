package com.example.fleet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Component
public class GpsLogCleanupTask {

    @Value("${gps.cleanup.days:30}")
    private int cleanupDays;

    @Autowired
    private GPSLogRepository gpsLogRepo;

    // Testing
    // @Scheduled(cron = "0 * * * * *") // setiap menit
    // Jalankan setiap hari jam 2 pagi
    @Scheduled(cron = "0 0 2 * * *")
    public void cleanOldLogs() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(cleanupDays);
        int deleted = gpsLogRepo.deleteByTimestampBefore(threshold);
        System.out.println("Deleted " + deleted + " old GPS logs");
    }
}