package com.vuelosjanbi.airport.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.airport.application.ports.out.AirportRepositoryPort;
import com.vuelosjanbi.airport.domain.models.Airport;
import java.util.List;

@Service
public class AirportService {

  @Autowired
  private AirportRepositoryPort airportRepositoryPort;

  public AirportService(AirportRepositoryPort airportRepositoryPort) {
    this.airportRepositoryPort = airportRepositoryPort;
  }

  public Airport createAirport(Airport airport) {
    return airportRepositoryPort.save(airport);
  }

  public Airport getAirportById(Long id) {
    return airportRepositoryPort.findById(id).orElse(null);
  }

  public void deleteAirportById(Long id) {
    airportRepositoryPort.deleteById(id);
  }

  public Airport updateAirport(Airport airport) {
    return airportRepositoryPort.save(airport);
  }

  public List<Airport> getAllAirports() {
    return airportRepositoryPort.findAll();
  }

}
