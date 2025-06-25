package com.spms.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private Long parkingSpaceId;
    private BigDecimal amount;
    private Integer parkingDurationHours;
    private BigDecimal hourlyRate;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private String cardNumberMasked;
    private LocalDateTime paymentDate;
    private LocalDateTime parkingStartTime;
    private LocalDateTime parkingEndTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, DIGITAL_WALLET, CASH
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, REFUNDED, CANCELLED
    }

    // Constructors
    public PaymentDTO() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getParkingDurationHours() {
        return parkingDurationHours;
    }

    public void setParkingDurationHours(Integer parkingDurationHours) {
        this.parkingDurationHours = parkingDurationHours;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCardNumberMasked() {
        return cardNumberMasked;
    }

    public void setCardNumberMasked(String cardNumberMasked) {
        this.cardNumberMasked = cardNumberMasked;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getParkingStartTime() {
        return parkingStartTime;
    }

    public void setParkingStartTime(LocalDateTime parkingStartTime) {
        this.parkingStartTime = parkingStartTime;
    }

    public LocalDateTime getParkingEndTime() {
        return parkingEndTime;
    }

    public void setParkingEndTime(LocalDateTime parkingEndTime) {
        this.parkingEndTime = parkingEndTime;
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
