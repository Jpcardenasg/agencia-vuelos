package com.vuelosjanbi.customer.infrastructure.adapters.out;

import java.util.List;

import com.vuelosjanbi.customer.application.ports.out.CustomerRepositoryPort;
import com.vuelosjanbi.customer.domain.models.Customer;

public class MySQLCustomerRepository implements CustomerRepositoryPort {

  @Override
  public Customer save(Customer customer) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public Customer findById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void deleteById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public List<Customer> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

}
