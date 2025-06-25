package com.spms.payment.service;

import com.spms.payment.dto.PaymentDTO;
import com.spms.payment.entity.Payment.PaymentMethod;
import com.spms.payment.entity.Payment.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<PaymentDTO> getAllPayments();

    Optional<PaymentDTO> getPaymentById(Long id);

    Optional<PaymentDTO> getPaymentByTransactionId(String transactionId);

    List<PaymentDTO> getPaymentsByUserId(Long userId);

    List<PaymentDTO> getPaymentsByVehicleId(Long vehicleId);

    List<PaymentDTO> getPaymentsByParkingSpaceId(Long parkingSpaceId);

    List<PaymentDTO> getPaymentsByStatus(PaymentStatus paymentStatus);

    List<PaymentDTO> getPaymentsByMethod(PaymentMethod paymentMethod);

    List<PaymentDTO> getUserPaymentsByStatus(Long userId, PaymentStatus paymentStatus);

    List<PaymentDTO> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<PaymentDTO> getUserPaymentsBetweenDates(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    PaymentDTO createPayment(PaymentDTO paymentDTO);

    PaymentDTO processPayment(Long paymentId, String cardNumber, String cardHolderName,
                              String expiryDate, String cvv);

    PaymentDTO refundPayment(Long paymentId);

    PaymentDTO cancelPayment(Long paymentId);

    void deletePayment(Long id);

    BigDecimal getTotalRevenue();

    BigDecimal getUserTotalPayments(Long userId);

    Long getCompletedPaymentsCount();

    Long getPendingPaymentsCount();
}
