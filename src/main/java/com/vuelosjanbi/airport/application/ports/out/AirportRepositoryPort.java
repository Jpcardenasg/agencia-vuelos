package com.vuelosjanbi.airport.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.airport.domain.models.Airport;

public interface AirportRepositoryPort {

  Airport save(Airport airport);

  void deleteById(Long airportId);

  List<Airport> findAll();

  Optional<Airport> findById(Long airportId);

  Optional<Airport> findByName(String name);

}
