package com.vuelosjanbi.customer.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.customer.application.ports.out.CustomerRepositoryPort;
import com.vuelosjanbi.customer.domain.models.Customer;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepositoryPort customerRepositoryPort;

    public CustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    public Customer createCustomer(Customer Customer) {
        return customerRepositoryPort.save(Customer);
    }

    public Optional<Customer> getCustomer(String customerId) {
        return customerRepositoryPort.findById(customerId);
    }

    public Customer updateCustomer(Customer Customer) {
        return customerRepositoryPort.save(Customer);
    }

    public void deleteCustomer(String CustomerId) {
        customerRepositoryPort.deleteById(CustomerId);
    }

    public List<Customer> getAllCustomers() {
        return customerRepositoryPort.findAll();
    }

}
