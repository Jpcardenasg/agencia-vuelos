package com.vuelosjanbi.paymentMethod.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.paymentMethod.application.ports.out.PaymentMethodRepositoryPort;
import com.vuelosjanbi.paymentMethod.domain.PaymentMethod;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepositoryPort paymentMethodRepositoryPort;

    public PaymentMethodService(PaymentMethodRepositoryPort paymentMethodRepositoryPort) {
        this.paymentMethodRepositoryPort = paymentMethodRepositoryPort;
    }

    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepositoryPort.save(paymentMethod);
    }

    public void deletePaymentMethod(Long paymentMethodId) {
        paymentMethodRepositoryPort.deleteById(paymentMethodId);
    }

    public Optional<PaymentMethod> getPaymentMethodById(Long paymentMethodId) {
        return paymentMethodRepositoryPort.findById(paymentMethodId);
    }

    public Optional<PaymentMethod> getPaymentMethodByMethod(String mehtod) {
        return paymentMethodRepositoryPort.findByMethod(mehtod);
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepositoryPort.findAll();
    }

}
