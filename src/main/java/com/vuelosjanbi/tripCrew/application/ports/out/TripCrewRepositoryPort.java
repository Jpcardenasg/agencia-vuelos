package com.vuelosjanbi.tripCrew.application.ports.out;

import java.util.Optional;

import com.vuelosjanbi.tripCrew.domain.models.TripCrew;
import com.vuelosjanbi.tripCrew.domain.models.TripCrewId;

import java.util.List;

public interface TripCrewRepositoryPort {

  TripCrew save(TripCrew trip);

  Optional<TripCrew> findById(TripCrewId id);

  void deleteById(Long id);

  List<TripCrew> findAll();

}
