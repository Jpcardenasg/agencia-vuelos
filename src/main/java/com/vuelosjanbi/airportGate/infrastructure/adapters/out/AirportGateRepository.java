package com.vuelosjanbi.airportGate.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.airportGate.application.port.out.AirportGateRepositoryPort;
import com.vuelosjanbi.airportGate.domain.models.AirportGate;

@Repository
public interface AirportGateRepository extends JpaRepository<AirportGate, Long>, AirportGateRepositoryPort {

}
 
