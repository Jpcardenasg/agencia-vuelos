package com.vuelosjanbi.flightConnection.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;

public interface FlightConnectionsRepositoryPort {

  FlightConnection save(FlightConnection flightConnection);

  void deleteById(Long id);

  Optional<FlightConnection> findById(Long id);

  List<FlightConnection> findByPlaneId(Long planeId);

  List<FlightConnection> findAll();

  List<FlightConnection> findByPlanePlate(String plate);

}
