package com.vuelosjanbi.flightFare.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.flightFare.domain.models.FlightFare;

public interface FlightFareRepositoryPort {

    FlightFare save(FlightFare flightFare);

    void deleteById(Long id);

    Optional<FlightFare> findById(Long id);

    List<FlightFare> findAll();
}