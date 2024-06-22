package com.vuelosjanbi.tripCrew.domain.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class TripCrewId {
  private Long employee;
  private Long flightConnection;

  public Long getEmployee() {
    return employee;
  }

  public void setEmployee(Long employeeId) {
    this.employee = employeeId;
  }

  public Long getFlightConnection() {
    return flightConnection;
  }

  public void setFlightConnection(Long flightConnectionId) {
    this.flightConnection = flightConnectionId;
  }
}
