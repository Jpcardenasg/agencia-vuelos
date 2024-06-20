package com.vuelosjanbi.customer.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.customer.domain.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
