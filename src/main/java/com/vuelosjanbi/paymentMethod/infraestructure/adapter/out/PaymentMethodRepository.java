package com.vuelosjanbi.paymentMethod.infraestructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.paymentMethod.application.ports.out.PaymentMethodRepositoryPort;
import com.vuelosjanbi.paymentMethod.domain.PaymentMethod;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long>, PaymentMethodRepositoryPort {
}
