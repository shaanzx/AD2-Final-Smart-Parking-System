package com.spms.payment.service.impl;

import com.spms.payment.dto.PaymentDTO;
import com.spms.payment.entity.Payment;
import com.spms.payment.entity.Payment.PaymentMethod;
import com.spms.payment.entity.Payment.PaymentStatus;
import com.spms.payment.repository.PaymentRepository;
import com.spms.payment.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Random random = new Random();

    private PaymentDTO convertToDto(Payment payment) {
        return modelMapper.map(payment, PaymentDTO.class);
    }

    private Payment convertToEntity(PaymentDTO paymentDTO) {
        return modelMapper.map(paymentDTO, Payment.class);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentDTO> getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(this::convertToDto);
    }

    @Override
    public Optional<PaymentDTO> getPaymentByTransactionId(String transactionId) {
        Optional<Payment> payment = paymentRepository.findByTransactionId(transactionId);
        return payment.map(this::convertToDto);
    }

    @Override
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);
        return payments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByVehicleId(Long vehicleId) {
        List<Payment> payments = paymentRepository.findByVehicleId(vehicleId);
        return payments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByParkingSpaceId(Long parkingSpaceId) {
        List<Payment> payments = paymentRepository.findByParkingSpaceId(parkingSpaceId);
        return payments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByStatus(PaymentStatus paymentStatus) {
        List<Payment> payments = paymentRepository.findByPaymentStatus(paymentStatus);
        return payments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByMethod(PaymentMethod paymentMethod) {
        List<Payment> payments = paymentRepository.findByPaymentMethod(paymentMethod);
        return payments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getUserPaymentsByStatus(Long userId, PaymentStatus paymentStatus) {
        List<Payment> payments = paymentRepository.findByUserIdAndPaymentStatus(userId, paymentStatus);
        return payments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Payment> payments = paymentRepository.findPaymentsBetweenDates(startDate, endDate);
        return payments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getUserPaymentsBetweenDates(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Payment> payments = paymentRepository.findUserPaymentsBetweenDates(userId, startDate, endDate);
        return payments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);

        BigDecimal calculatedAmount = payment.getHourlyRate()
                .multiply(BigDecimal.valueOf(payment.getParkingDurationHours()));
        payment.setAmount(calculatedAmount);

        payment.setParkingStartTime(LocalDateTime.now());
        payment.setParkingEndTime(LocalDateTime.now().plusHours(payment.getParkingDurationHours()));

        Payment saved = paymentRepository.save(payment);
        return convertToDto(saved);
    }

    @Override
    public PaymentDTO processPayment(Long paymentId, String cardNumber, String cardHolderName,
                                     String expiryDate, String cvv) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));

        if (payment.getPaymentStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Payment is not in pending status");
        }

        boolean isValidCard = validateMockCard(cardNumber, cardHolderName, expiryDate, cvv);

        if (isValidCard) {
            boolean paymentSuccess = simulatePaymentProcessing();

            if (paymentSuccess) {
                payment.setPaymentStatus(PaymentStatus.COMPLETED);
                payment.setPaymentDate(LocalDateTime.now());
                payment.setCardNumberMasked(maskCardNumber(cardNumber));
            } else {
                payment.setPaymentStatus(PaymentStatus.FAILED);
            }
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }

        Payment updated = paymentRepository.save(payment);
        return convertToDto(updated);
    }

    @Override
    public PaymentDTO refundPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));

        if (payment.getPaymentStatus() != PaymentStatus.COMPLETED) {
            throw new RuntimeException("Only completed payments can be refunded");
        }

        boolean refundSuccess = simulateRefundProcessing();

        if (refundSuccess) {
            payment.setPaymentStatus(PaymentStatus.REFUNDED);
        } else {
            throw new RuntimeException("Refund processing failed");
        }

        Payment updated = paymentRepository.save(payment);
        return convertToDto(updated);
    }

    @Override
    public PaymentDTO cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));

        if (payment.getPaymentStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Only pending payments can be cancelled");
        }

        payment.setPaymentStatus(PaymentStatus.CANCELLED);
        Payment updated = paymentRepository.save(payment);
        return convertToDto(updated);
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));

        if (payment.getPaymentStatus() == PaymentStatus.COMPLETED) {
            throw new RuntimeException("Cannot delete completed payment");
        }

        paymentRepository.delete(payment);
    }

    @Override
    public BigDecimal getTotalRevenue() {
        return paymentRepository.getTotalAmountByStatus(PaymentStatus.COMPLETED);
    }

    @Override
    public BigDecimal getUserTotalPayments(Long userId) {
        return paymentRepository.getTotalAmountByUserAndStatus(userId, PaymentStatus.COMPLETED);
    }

    @Override
    public Long getCompletedPaymentsCount() {
        return paymentRepository.countByPaymentStatus(PaymentStatus.COMPLETED);
    }

    @Override
    public Long getPendingPaymentsCount() {
        return paymentRepository.countByPaymentStatus(PaymentStatus.PENDING);
    }

    // Mock validation and processing methods
    private boolean validateMockCard(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        // Simple mock validation
        if (cardNumber == null || cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            return false;
        }
        if (expiryDate == null || !expiryDate.matches("\\d{2}/\\d{2}")) {
            return false;
        }
        if (cvv == null || cvv.length() < 3 || cvv.length() > 4) {
            return false;
        }

        // Simulate card validation failure for testing (10% chance)
        return random.nextInt(10) != 0;
    }

    private boolean simulatePaymentProcessing() {
        // Simulate payment processing with 95% success rate
        return random.nextInt(100) < 95;
    }

    private boolean simulateRefundProcessing() {
        // Simulate refund processing with 98% success rate
        return random.nextInt(100) < 98;
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
