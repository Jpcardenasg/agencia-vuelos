package com.vuelosjanbi.payment.domain;

import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.paymentMethod.domain.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long cardNumber;

  @ManyToOne
  private PaymentMethod paymentMethod;

  @ManyToOne
  private Customer customer;

  public Payment() {
  }

  public Payment(Long cardNumber, PaymentMethod paymentMethod) {
    this.cardNumber = cardNumber;
    this.paymentMethod = paymentMethod;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Long cardNumber) {
    this.cardNumber = cardNumber;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  @Override
  public String toString() {
    return "Seat{" +
        "id=" + id +
        ", cardNumber='" + cardNumber + '\'' +
        ", paymentMethod=" + paymentMethod.getMethod() +
        '}';
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

}
