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
  @MapsId("employeeId")
  private Employee employee;

  @ManyToOne
  @MapsId("flightConnectionId")
  private FlightConnection flightConnection;

  public TripCrew() {
  }

  public TripCrew(Employee employee, FlightConnection flightConnection) {
    this.employee = employee;
    this.flightConnection = flightConnection;
    this.id = new TripCrewId(employee.getId(), flightConnection.getId());
  }

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

  @Override
  public String toString() {
    return "TripCrew [id=" + id + ", employee=" + employee + ", flightConnection=" + flightConnection + "]";
  }

}
