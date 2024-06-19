package com.vuelosjanbi.airline.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.airline.application.ports.out.AirlineRepositoryPort;
import com.vuelosjanbi.airline.domain.models.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>, AirlineRepositoryPort {

}
