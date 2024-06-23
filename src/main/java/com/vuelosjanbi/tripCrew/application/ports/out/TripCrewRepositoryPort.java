package com.vuelosjanbi.tripCrew.application.ports.out;

import java.util.Optional;

import com.vuelosjanbi.tripCrew.domain.models.TripCrew;

import java.util.List;

public interface TripCrewRepositoryPort {

  TripCrew save(TripCrew trip);

  Optional<TripCrew> findById(TripCrew id);

  void deleteById(TripCrew id);

  List<TripCrew> findAll();

}
