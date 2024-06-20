package com.vuelosjanbi.airport.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.airline.domain.models.Airline;

public interface AirportRepositoryPort {

  Airline save(Airline airline);

  void deleteById(Long airlineId);

  List<Airline> findAll();

  Optional<Airline> findById(Long airlineId);

}
