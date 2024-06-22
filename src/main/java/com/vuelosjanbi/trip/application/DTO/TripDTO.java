package com.vuelosjanbi.trip.application.DTO;

import java.sql.Date;
import java.util.List;

import com.vuelosjanbi.flightConnection.application.DTO.FlightConnectionDTO;
import com.vuelosjanbi.tripBooking.application.DTO.TripBookingDTO;

public class TripDTO {
  private Long id;
  private Date tripDate;
  private double tripPrice;

  List<FlightConnectionDTO> flightConnections;
  List<TripBookingDTO> tripBookings;

  public TripDTO() {
  }

  public TripDTO(Long id, Date tripDate, double tripPrice, List<FlightConnectionDTO> flightConnections,
      List<TripBookingDTO> tripBookings) {
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

  public List<FlightConnectionDTO> getFlightConnections() {
    return flightConnections;
  }

  public void setFlightConnections(List<FlightConnectionDTO> flightConnections) {
    this.flightConnections = flightConnections;
  }

  public List<TripBookingDTO> getTripBookings() {
    return tripBookings;
  }

  public void setTripBookings(List<TripBookingDTO> tripBookings) {
    this.tripBookings = tripBookings;
  }

}
