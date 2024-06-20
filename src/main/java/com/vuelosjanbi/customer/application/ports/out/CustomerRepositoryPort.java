package com.vuelosjanbi.customer.application.ports.out;

import java.util.List;

import com.vuelosjanbi.customer.domain.models.Customer;

public interface CustomerRepositoryPort {
  Customer save(Customer customer);

  Customer findById(Long id);

  void deleteById(Long id);

  List<Customer> findAll();
}
