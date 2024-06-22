package com.vuelosjanbi.tripCrew.domain.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class TripCrewId implements Serializable {
  private Long employeeId;
  private Long flightConnectionId;

  public TripCrewId() {
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Long getFlightConnectionId() {
    return flightConnectionId;
  }

  public void setFlightConnectionId(Long flightConnectionId) {
    this.flightConnectionId = flightConnectionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    TripCrewId that = (TripCrewId) o;
    return Objects.equals(employeeId, that.employeeId) &&
        Objects.equals(flightConnectionId, that.flightConnectionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employeeId, flightConnectionId);
  }

}
