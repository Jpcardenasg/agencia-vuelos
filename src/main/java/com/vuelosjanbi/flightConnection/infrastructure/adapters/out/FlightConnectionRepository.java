package com.vuelosjanbi.flightConnection.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionsRepositoryPort;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;

public interface FlightConnectionRepository
        extends JpaRepository<FlightConnection, Long>, FlightConnectionsRepositoryPort {

}
