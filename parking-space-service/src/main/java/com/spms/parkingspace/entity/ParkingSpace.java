package com.spms.parkingspace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Space number is required")
    @Column(name = "space_number", unique = true, nullable = false)
    private String spaceNumber;
    
    @NotBlank(message = "Location is required")
    @Column(name = "location", nullable = false)
    private String location;
    
    @NotBlank(message = "Zone is required")
    @Column(name = "zone", nullable = false)
    private String zone;
    
    @NotBlank(message = "City is required")
    @Column(name = "city", nullable = false)
    private String city;
    
    @NotNull(message = "Hourly rate is required")
    @Positive(message = "Hourly rate must be positive")
    @Column(name = "hourly_rate", nullable = false)
    private Double hourlyRate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ParkingStatus status = ParkingStatus.AVAILABLE;
    
    @Column(name = "owner_id")
    private Long ownerId;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "reserved_until")
    private LocalDateTime reservedUntil;
    
    public enum ParkingStatus {
        AVAILABLE, OCCUPIED, RESERVED, OUT_OF_ORDER
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public ParkingSpace() {}
    
    public ParkingSpace(String spaceNumber, String location, String zone, String city, Double hourlyRate, Long ownerId) {
        this.spaceNumber = spaceNumber;
        this.location = location;
        this.zone = zone;
        this.city = city;
        this.hourlyRate = hourlyRate;
        this.ownerId = ownerId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSpaceNumber() {
        return spaceNumber;
    }
    
    public void setSpaceNumber(String spaceNumber) {
        this.spaceNumber = spaceNumber;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getZone() {
        return zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public Double getHourlyRate() {
        return hourlyRate;
    }
    
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    public ParkingStatus getStatus() {
        return status;
    }
    
    public void setStatus(ParkingStatus status) {
        this.status = status;
    }
    
    public Long getOwnerId() {
        return ownerId;
    }
    
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public LocalDateTime getReservedUntil() {
        return reservedUntil;
    }
    
    public void setReservedUntil(LocalDateTime reservedUntil) {
        this.reservedUntil = reservedUntil;
    }
}