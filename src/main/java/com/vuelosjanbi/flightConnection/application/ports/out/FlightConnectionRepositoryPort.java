package com.vuelosjanbi.flightConnection.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.trip.domain.models.Trip;

public interface FlightConnectionRepositoryPort {

  FlightConnection save(FlightConnection flightConnection);

  void deleteById(Long id);

  Optional<FlightConnection> findById(Long id);

  List<FlightConnection> findByPlaneId(Long planeId);

  List<FlightConnection> findAll();

  List<FlightConnection> findByPlanePlate(String plate);

  Optional<FlightConnection> findByTripId(Long tripId);

  List<FlightConnection> findByTrip(Trip trip);

}
