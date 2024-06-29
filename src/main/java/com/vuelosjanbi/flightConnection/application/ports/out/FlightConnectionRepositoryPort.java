package com.vuelosjanbi.flightConnection.application.ports.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

  @Query("SELECT fc FROM FlightConnection fc JOIN FETCH fc.seats WHERE fc.id = :flightConnectionId")
  FlightConnection findByIdWithSeats(@Param("flightConnectionId") Long flightConnectionId);

}
