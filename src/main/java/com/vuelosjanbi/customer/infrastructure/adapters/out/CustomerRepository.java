package com.vuelosjanbi.customer.infrastructure.adapters.out;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.customer.application.ports.out.CustomerRepositoryPort;
import com.vuelosjanbi.customer.domain.models.Customer;

@Primary
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, CustomerRepositoryPort {

}
