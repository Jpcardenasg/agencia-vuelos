package com.vuelosjanbi.flightConnection.application.DTO;

import com.vuelosjanbi.airport.application.DTO.AirportDTO;
import com.vuelosjanbi.plane.application.DTO.PlaneDTO;
import com.vuelosjanbi.trip.application.DTO.TripDTO;

public class FlightConnectionDTO {
  private Long id;
  private String connectionNumber;

  private TripDTO trip;

  private PlaneDTO plane;

  private AirportDTO airport;

  public FlightConnectionDTO() {
  }

  public FlightConnectionDTO(Long id, String connectionNumber, TripDTO trip, PlaneDTO plane, AirportDTO airport) {
    this.id = id;
    this.connectionNumber = connectionNumber;
    this.trip = trip;
    this.plane = plane;
    this.airport = airport;
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

  public TripDTO getTrip() {
    return trip;
  }

  public void setTrip(TripDTO trip) {
    this.trip = trip;
  }

  public PlaneDTO getPlane() {
    return plane;
  }

  public void setPlane(PlaneDTO plane) {
    this.plane = plane;
  }

  public AirportDTO getAirport() {
    return airport;
  }

  public void setAirport(AirportDTO airport) {
    this.airport = airport;
  }

}
