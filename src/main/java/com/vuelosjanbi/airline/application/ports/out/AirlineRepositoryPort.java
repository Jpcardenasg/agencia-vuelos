package com.vuelosjanbi.airline.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.airline.domain.models.Airline;

public interface AirlineRepositoryPort {

  Airline save(Airline airline);

  void deleteById(Long airlineId);

  Optional<Airline> findById(Long airlineId);

  Optional<Airline> findByName(String airlineName);

  List<Airline> findAll();

}
