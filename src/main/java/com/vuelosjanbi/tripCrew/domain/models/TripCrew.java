package com.vuelosjanbi.tripCrew.domain.models;

import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TripCrew {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Employee employee;

  @ManyToOne
  private FlightConnection flightConnection;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
