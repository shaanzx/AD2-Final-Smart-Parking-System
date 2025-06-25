package com.spms.vehicle.dto;

import com.spms.vehicle.entity.Vehicle.VehicleType;
import java.time.LocalDateTime;

public class VehicleDTO {

    private Long id;
    private String licensePlate;
    private String make;
    private String model;
    private Integer year;
    private String color;
    private VehicleType vehicleType = VehicleType.CAR;
    private Long userId;
    private Long currentParkingSpaceId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public VehicleDTO() {}

    public VehicleDTO(Long id, String licensePlate, String make, String model, Integer year, String color,
                      VehicleType vehicleType, Long userId, Long currentParkingSpaceId,
                      LocalDateTime entryTime, LocalDateTime exitTime,
                      LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vehicleType = vehicleType;
        this.userId = userId;
        this.currentParkingSpaceId = currentParkingSpaceId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCurrentParkingSpaceId() {
        return currentParkingSpaceId;
    }

    public void setCurrentParkingSpaceId(Long currentParkingSpaceId) {
        this.currentParkingSpaceId = currentParkingSpaceId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
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
}
