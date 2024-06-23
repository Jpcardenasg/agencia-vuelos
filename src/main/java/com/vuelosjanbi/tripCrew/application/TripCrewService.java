package com.vuelosjanbi.tripCrew.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionRepositoryPort;
import com.vuelosjanbi.tripCrew.application.ports.out.TripCrewRepositoryPort;
import com.vuelosjanbi.tripCrew.domain.models.TripCrew;

@Service
public class TripCrewService {

  @Autowired
  private TripCrewRepositoryPort tripCrewRepositoryPort;

  public TripCrewService(TripCrewRepositoryPort tripCrewRepositoryPort) {
    this.tripCrewRepositoryPort = tripCrewRepositoryPort;
  }

  public TripCrewService() {
  }

  public TripCrewService(TripCrewRepositoryPort tripCrewRepositoryPort, EmployeeRepositoryPort employeeRepositoryPort,
      FlightConnectionRepositoryPort flightConnectionRepositoryPort) {
    this.tripCrewRepositoryPort = tripCrewRepositoryPort;
  }

  public TripCrew createTripCrew(TripCrew tripCrew) {
    return tripCrewRepositoryPort.save(tripCrew);
  }

  public TripCrew getTripCrewById(TripCrew id) {
    return tripCrewRepositoryPort.findById(id).orElse(null);
  }

  public void deleteTripCrewById(TripCrew id) {
    tripCrewRepositoryPort.deleteById(id);
  }

  public TripCrew updateTripCrew(TripCrew tripCrew) {
    return tripCrewRepositoryPort.save(tripCrew);
  }

  public List<TripCrew> getAllTripCrews() {
    return tripCrewRepositoryPort.findAll();
  }

}
