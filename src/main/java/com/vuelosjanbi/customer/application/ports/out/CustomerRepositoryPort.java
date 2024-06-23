package com.vuelosjanbi.customer.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.customer.domain.models.Customer;

public interface CustomerRepositoryPort {
  Customer save(Customer customer);

  Optional<Customer> findById(String id);

  void deleteById(String id);

  List<Customer> findAll();

}
