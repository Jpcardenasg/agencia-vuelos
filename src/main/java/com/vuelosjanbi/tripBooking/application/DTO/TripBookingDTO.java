package com.vuelosjanbi.tripBooking.application.DTO;

import java.sql.Date;

import com.vuelosjanbi.trip.application.DTO.TripDTO;

public class TripBookingDTO {
  private Long id;
  private Date date;
  TripDTO trip;

  public TripBookingDTO() {
  }

  public TripBookingDTO(Long id, Date date, TripDTO trip) {
    this.id = id;
    this.date = date;
    this.trip = trip;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public TripDTO getTrip() {
    return trip;
  }

  public void setTrip(TripDTO trip) {
    this.trip = trip;
  }

}
