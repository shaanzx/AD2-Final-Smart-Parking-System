package com.spms.parkingspace.dto;

import java.time.LocalDateTime;

public class ParkingSpaceDTO {

    private Long id;
    private String spaceNumber;
    private String location;
    private String zone;
    private String city;
    private Double hourlyRate;
    private ParkingStatus status;
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime reservedUntil;

    public enum ParkingStatus {
        AVAILABLE, OCCUPIED, RESERVED, OUT_OF_ORDER
    }

    // Constructors
    public ParkingSpaceDTO() {}

    public ParkingSpaceDTO(Long id, String spaceNumber, String location, String zone, String city,
                           Double hourlyRate, ParkingStatus status, Long ownerId,
                           LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime reservedUntil) {
        this.id = id;
        this.spaceNumber = spaceNumber;
        this.location = location;
        this.zone = zone;
        this.city = city;
        this.hourlyRate = hourlyRate;
        this.status = status;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reservedUntil = reservedUntil;
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
