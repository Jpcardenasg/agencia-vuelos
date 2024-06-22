package com.vuelosjanbi.tripCrew.domain.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class TripCrewId implements Serializable {
  private Long employee;
  private Long flightConnection;

  public TripCrewId() {
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    TripCrewId that = (TripCrewId) o;
    return Objects.equals(employee, that.employee) &&
        Objects.equals(flightConnection, that.flightConnection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employee, flightConnection);
  }

}
