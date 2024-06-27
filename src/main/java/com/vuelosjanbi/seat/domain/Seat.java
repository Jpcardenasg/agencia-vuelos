package com.vuelosjanbi.seat.domain;

import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String seatNumber;
  private String seatClass;
  private boolean isAvailable;

  @ManyToOne
  private FlightConnection flightConnection;

  public Seat() {
  }

  public Seat(String seatNumber, String seatClass, boolean isAvailable, FlightConnection flightConnection) {
    this.seatNumber = seatNumber;
    this.seatClass = seatClass;
    this.isAvailable = isAvailable;
    this.flightConnection = flightConnection;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(String seatNumber) {
    this.seatNumber = seatNumber;
  }

  public String getSeatClass() {
    return seatClass;
  }

  public void setSeatClass(String seatClass) {
    this.seatClass = seatClass;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public FlightConnection getFlightConnection() {
    return flightConnection;
  }

  public void setFlightConnection(FlightConnection flightConnection) {
    this.flightConnection = flightConnection;
  }

  @Override
  public String toString() {
    return "Seat{" +
        "id=" + id +
        ", seatNumber='" + seatNumber + '\'' +
        ", seatClass='" + seatClass + '\'' +
        ", isAvailable=" + isAvailable +
        ", flightConnection=" + flightConnection +
        '}';
  }
}
