package com.spms.payment.repository;

import com.spms.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    Optional<Payment> findByTransactionId(String transactionId);
    
    List<Payment> findByUserId(Long userId);
    
    List<Payment> findByVehicleId(Long vehicleId);
    
    List<Payment> findByParkingSpaceId(Long parkingSpaceId);
    
    List<Payment> findByPaymentStatus(Payment.PaymentStatus paymentStatus);
    
    List<Payment> findByPaymentMethod(Payment.PaymentMethod paymentMethod);
    
    List<Payment> findByUserIdAndPaymentStatus(Long userId, Payment.PaymentStatus paymentStatus);
    
    @Query("SELECT p FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    List<Payment> findPaymentsBetweenDates(@Param("startDate") LocalDateTime startDate, 
                                          @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT p FROM Payment p WHERE p.userId = :userId AND p.paymentDate BETWEEN :startDate AND :endDate")
    List<Payment> findUserPaymentsBetweenDates(@Param("userId") Long userId, 
                                              @Param("startDate") LocalDateTime startDate, 
                                              @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentStatus = :status")
    BigDecimal getTotalAmountByStatus(@Param("status") Payment.PaymentStatus status);
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.userId = :userId AND p.paymentStatus = :status")
    BigDecimal getTotalAmountByUserAndStatus(@Param("userId") Long userId, 
                                           @Param("status") Payment.PaymentStatus status);
    
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.paymentStatus = :status")
    Long countByPaymentStatus(@Param("status") Payment.PaymentStatus status);
}