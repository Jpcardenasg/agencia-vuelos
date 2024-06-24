package com.vuelosjanbi.customer.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.customer.domain.models.Customer;

public interface CustomerRepositoryPort {

  Customer save(Customer customer);

  void deleteById(String id);

  Optional<Customer> findById(String id);

  List<Customer> findAll();

}
