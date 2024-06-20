package com.vuelosjanbi.flightConnection.domain.models;

import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.plane.domain.models.Plane;
// import com.vuelosjanbi.trip.domain.models.Trip;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FlightConnection {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String connectionNumber;

  // TODO:Cambiar el tipo a trip
  // @ManyToOne
  private String trip;
  @ManyToOne
  private Plane plane;
  @ManyToOne
  private Airport airport;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getConnectionNumber() {
    return connectionNumber;
  }

  public void setConnectionNumber(String connectionNumber) {
    this.connectionNumber = connectionNumber;
  }

  public String getTrip() {
    return trip;
  }

  public void setTrip(String trip) {
    this.trip = trip;
  }

  public Plane getPlane() {
    return plane;
  }

  public void setPlane(Plane plane) {
    this.plane = plane;
  }

  public Airport getAirport() {
    return airport;
  }

  public void setAirport(Airport airport) {
    this.airport = airport;
  }

}
