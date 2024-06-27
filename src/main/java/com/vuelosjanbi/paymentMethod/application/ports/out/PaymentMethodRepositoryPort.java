package com.vuelosjanbi.paymentMethod.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.paymentMethod.domain.PaymentMethod;

public interface PaymentMethodRepositoryPort {

  PaymentMethod save(PaymentMethod paymentMethod);

  void deleteById(Long id);

  Optional<PaymentMethod> findById(Long id);

  Optional<PaymentMethod> findByMethod(String method);

  List<PaymentMethod> findAll();
}
