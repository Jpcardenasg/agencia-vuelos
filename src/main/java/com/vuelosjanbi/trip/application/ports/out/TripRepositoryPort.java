package com.vuelosjanbi.trip.application.ports.out;

import com.vuelosjanbi.trip.domain.models.Trip;
import java.util.List;
import java.util.Optional;

public interface TripRepositoryPort {

  Trip save(Trip trip);

  List<Trip> findAll();

  Optional<Trip> findById(Long id);

  void deleteById(Long id);


}
