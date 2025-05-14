package com.example.fleet;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "gps_log",
    indexes = {
        @Index(name = "idx_vehicle_timestamp", columnList = "vehicle_id, timestamp"),
        @Index(name = "idx_speed_violation", columnList = "speedViolation")
    }
)
public class GPSLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    private double latitude;
    private double longitude;
    private double speed;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private boolean speedViolation;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public boolean isSpeedViolation() { return speedViolation; }
    public void setSpeedViolation(boolean speedViolation) { this.speedViolation = speedViolation; }
}