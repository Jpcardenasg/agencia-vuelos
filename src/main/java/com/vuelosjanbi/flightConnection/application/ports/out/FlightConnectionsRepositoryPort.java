package com.vuelosjanbi.flightConnection.application.ports.out;

import java.util.List;

import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;

public interface FlightConnectionsRepositoryPort {
  FlightConnection save(FlightConnection flightConnection);

  FlightConnection findById(Long id);

  void deleteById(Long id);

  List<FlightConnection> findAll();

  List<FlightConnection> findByPlaneId(Long planeId);
}
