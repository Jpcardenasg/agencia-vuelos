package com.vuelosjanbi.payment.infraestructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.payment.application.ports.out.PaymentRepositoryPort;
import com.vuelosjanbi.payment.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryPort {

}
