package com.vuelosjanbi.airport.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.airport.application.ports.out.AirportRepositoryPort;
import com.vuelosjanbi.airport.domain.models.Airport;

@Repository
public interface AirporRepository extends JpaRepository<Airport, Long>, AirportRepositoryPort {

}
