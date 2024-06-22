package com.vuelosjanbi.tripCrew.domain.models;

import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class TripCrew {
  @EmbeddedId
  private TripCrewId id;

  @ManyToOne
  @MapsId("employee")
  private Employee employee;

  @ManyToOne
  @MapsId("flightConnection")
  private FlightConnection flightConnection;

  public TripCrewId getId() {
    return id;
  }

  public void setId(TripCrewId id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public FlightConnection getFlightConnection() {
    return flightConnection;
  }

  public void setFlightConnection(FlightConnection flightConnection) {
    this.flightConnection = flightConnection;
  }

}
