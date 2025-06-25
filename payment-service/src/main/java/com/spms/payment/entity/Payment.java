package com.spms.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "User ID is required")
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @NotNull(message = "Vehicle ID is required")
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;
    
    @NotNull(message = "Parking space ID is required")
    @Column(name = "parking_space_id", nullable = false)
    private Long parkingSpaceId;
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "parking_duration_hours", nullable = false)
    private Integer parkingDurationHours;
    
    @Column(name = "hourly_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal hourlyRate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    
    @Column(name = "transaction_id", unique = true)
    private String transactionId;
    
    @Column(name = "card_number_masked")
    private String cardNumberMasked;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    
    @Column(name = "parking_start_time")
    private LocalDateTime parkingStartTime;
    
    @Column(name = "parking_end_time")
    private LocalDateTime parkingEndTime;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, DIGITAL_WALLET, CASH
    }
    
    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, REFUNDED, CANCELLED
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (transactionId == null) {
            transactionId = generateTransactionId();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    // Constructors
    public Payment() {}
    
    public Payment(Long userId, Long vehicleId, Long parkingSpaceId, BigDecimal amount, 
                   Integer parkingDurationHours, BigDecimal hourlyRate, PaymentMethod paymentMethod) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.parkingSpaceId = parkingSpaceId;
        this.amount = amount;
        this.parkingDurationHours = parkingDurationHours;
        this.hourlyRate = hourlyRate;
        this.paymentMethod = paymentMethod;
    }
    
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