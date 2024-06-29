package com.vuelosjanbi.payment.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.payment.domain.Payment;
import com.vuelosjanbi.paymentMethod.domain.PaymentMethod;

public interface PaymentRepositoryPort {

  Payment save(Payment payment);

  Optional<Payment> findById(Long id);

  void deleteById(Long id);

  Optional<Payment> findByCardNumber(Long cardNumber);

  List<Payment> findByPaymentMethod(PaymentMethod paymentMethod);

  List<Payment> findAll();

  List<Payment> findByCustomerId(String userId);

}
