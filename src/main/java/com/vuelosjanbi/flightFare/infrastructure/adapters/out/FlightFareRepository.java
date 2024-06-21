package com.vuelosjanbi.flightFare.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.flightFare.application.ports.out.FlightFareRepositoryPort;
import com.vuelosjanbi.flightFare.domain.models.FlightFare;

public interface FlightFareRepository extends JpaRepository<FlightFare, Long>, FlightFareRepositoryPort {

}
