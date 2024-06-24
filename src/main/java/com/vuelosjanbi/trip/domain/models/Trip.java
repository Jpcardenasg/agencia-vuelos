package com.vuelosjanbi.trip.domain.models;

import java.sql.Date;
import java.util.List;

import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Trip {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date tripDate;
  private double tripPrice;

  @OneToMany(mappedBy = "trip")
  private List<FlightConnection> flightConnections;

  @OneToMany(mappedBy = "trip")
  private List<TripBooking> tripBookings;

  public Trip() {
  }

  public Trip(Date tripDate, double tripPrice, Airport originAirport, Airport destinationAirport) {
    this.tripDate = tripDate;
    this.tripPrice = tripPrice;
  }

  public Trip(Long id, Date tripDate, double tripPrice, List<FlightConnection> flightConnections,
      List<TripBooking> tripBookings) {
    this.id = id;
    this.tripDate = tripDate;
    this.tripPrice = tripPrice;
    this.flightConnections = flightConnections;
    this.tripBookings = tripBookings;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getTripDate() {
    return tripDate;
  }

  public void setTripDate(Date tripDate) {
    this.tripDate = tripDate;
  }

  public double getTripPrice() {
    return tripPrice;
  }

  public void setTripPrice(double tripPrice) {
    this.tripPrice = tripPrice;
  }

  public List<FlightConnection> getFlightConnections() {
    return flightConnections;
  }

  public void setFlightConnections(List<FlightConnection> flightConnections) {
    this.flightConnections = flightConnections;
  }

  public List<TripBooking> getTripBookings() {
    return tripBookings;
  }

  public void setTripBookings(List<TripBooking> tripBookings) {
    this.tripBookings = tripBookings;
  }

  // public Airport getOriginAirport() {
  // return originAirport;
  // }

  // public void setOriginAirport(Airport originAirport) {
  // this.originAirport = originAirport;
  // }

  // public Airport getDestinationAirport() {
  // return destinationAirport;
  // }

  // public void setDestinationAirport(Airport destinationAirport) {
  // this.destinationAirport = destinationAirport;
  // }

  // @Override
  // public String toString() {
  // return String.format("Trip id: %d, Trip date: %s, Trip price: %.2f, Origin
  // airport: %s, Destination airport: %s",
  // id, tripDate, tripPrice, originAirport.getName(),
  // destinationAirport.getName());
  // }

}
