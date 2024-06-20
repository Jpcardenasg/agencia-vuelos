package com.vuelosjanbi.airportGate.application.port.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.airportGate.domain.models.AirportGate;

public interface AirportGateRepositoryPort {

  AirportGate save(AirportGate airportGate);

  void deleteById(Long airportGateId);

  List<AirportGate> findAll();

  Optional<AirportGate> findById(Long airportGateId);
}
