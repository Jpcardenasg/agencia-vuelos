package com.vuelosjanbi.flightConnection.domain.models;

import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.plane.domain.models.Plane;
import com.vuelosjanbi.trip.domain.models.Trip;

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
  private String connection_number;

  @ManyToOne
  private Trip trip;
  @ManyToOne
  private Plane plane;
  @ManyToOne
  private Airport origin;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getConnection_number() {
    return connection_number;
  }

  public void setConnection_number(String connection_number) {
    this.connection_number = connection_number;
  }

  public Trip getTrip() {
    return trip;
  }

  public void setTrip(Trip trip) {
    this.trip = trip;
  }

  public Plane getPlane() {
    return plane;
  }

  public void setPlane(Plane plane) {
    this.plane = plane;
  }

  public Airport getOrigin() {
    return origin;
  }

  public void setOrigin(Airport origin) {
    this.origin = origin;
  }

}
