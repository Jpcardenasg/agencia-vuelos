package com.vuelosjanbi.flightConnection.domain.models;

import java.util.ArrayList;
import java.util.List;

import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.plane.domain.models.Plane;
import com.vuelosjanbi.seat.domain.Seat;
import com.vuelosjanbi.trip.domain.models.Trip;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class FlightConnection {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String connectionNumber;

  @ManyToOne
  private Trip trip;
  @ManyToOne
  private Plane plane;
  @ManyToOne
  private Airport originAirport;
  @ManyToOne
  private Airport destinationAirport;

  @OneToMany(mappedBy = "flightConnection")
  private List<Seat> seats;

  public FlightConnection() {
  }

  public FlightConnection(Long id, String connectionNumber) {
    this.id = id;
    this.connectionNumber = connectionNumber;
  }

  public FlightConnection(String connectionNumber) {
    this.connectionNumber = connectionNumber;
  }

  public FlightConnection(String connectionNumber, Trip trip, Plane plane, Airport originAirport,
      Airport destinationAirport) {
    this.connectionNumber = connectionNumber;
    this.trip = trip;
    this.plane = plane;
    this.originAirport = originAirport;
    this.destinationAirport = destinationAirport;
    initializeDefaultSeats();
  }

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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName()).append(" ")
        .append("\n\tid: ").append(id)
        .append(",\n\tconnectionNumber: ").append(connectionNumber)
        .append(",\n\ttrip: ").append(trip)
        .append(",\n\tplane: ").append(plane)
        .append(",\n\tairport origin: ").append(originAirport)
        .append(",\n\tairport destination: ").append(destinationAirport);
    return sb.toString();
  }

  public Airport getOriginAirport() {
    return originAirport;
  }

  public void setOriginAirport(Airport originAirport) {
    this.originAirport = originAirport;
  }

  public Airport getDestinationAirport() {
    return destinationAirport;
  }

  public void setDestinationAirport(Airport destinationAirport) {
    this.destinationAirport = destinationAirport;
  }

  private void initializeDefaultSeats() {
    seats = new ArrayList<>();
    for (int i = 1; i <= plane.getCapacity(); i++) {
      seats.add(new Seat("Economy" + i, "Economy", true, this));
    }
    for (int i = 1; i <= plane.getCapacity() - 20; i++) {
      seats.add(new Seat("Business" + i, "Business", true, this));
    }
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }

}
