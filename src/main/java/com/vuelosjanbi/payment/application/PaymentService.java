package com.vuelosjanbi.payment.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.payment.application.ports.out.PaymentRepositoryPort;
import com.vuelosjanbi.payment.domain.Payment;
import com.vuelosjanbi.paymentMethod.domain.PaymentMethod;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepositoryPort paymentRepositoryPort;

    public PaymentService(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    public Payment createPayment(Payment payment) {
        return paymentRepositoryPort.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        return paymentRepositoryPort.save(payment);
    }

    public void deletePayment(Long paymentId) {
        paymentRepositoryPort.deleteById(paymentId);
    }

    public Optional<Payment> getPaymentByCardNumber(Long cardNumber) {
        return paymentRepositoryPort.findByCardNumber(cardNumber);
    }

    public List<Payment> getPaymentByPaymentMethod(PaymentMethod paymentMethod) {
        return paymentRepositoryPort.findByPaymentMethod(paymentMethod);
    }

    public List<Payment> getAllPayments() {
        return paymentRepositoryPort.findAll();
    }
}
