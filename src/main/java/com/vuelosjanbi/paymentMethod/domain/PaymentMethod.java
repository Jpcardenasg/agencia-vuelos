package com.vuelosjanbi.paymentMethod.domain;

import java.util.List;

import com.vuelosjanbi.payment.domain.Payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class PaymentMethod {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String method;

  @OneToMany(mappedBy = "paymentMethod")
  private List<Payment> payments;

  public PaymentMethod() {
  }

  public PaymentMethod(String method) {
    this.method = method;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public List<Payment> getPayments() {
    return payments;
  }

  public void setPayments(List<Payment> payments) {
    this.payments = payments;
  }

  @Override
  public String toString() {
    return "Seat{" +
        "id=" + id +
        ", method='" + method + '\'' +
        '}';
  }
}
