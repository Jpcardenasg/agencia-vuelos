package com.vuelosjanbi.tripBooking.domain.models;

import java.sql.Date;

import com.vuelosjanbi.trip.domain.models.Trip;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TripBooking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date date;

  @ManyToOne
  private Trip trip;

  @OneToMany(mappedBy = "tripBooking", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<TripBookingDetail> tripBookingDetails;

  public TripBooking() {
  }

  public TripBooking(Date date, Trip trip) {
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

  public Trip getTrip() {
    return trip;
  }

  public void setTrip(Trip trip) {
    this.trip = trip;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("TripBooking - ")
        .append("ID: ").append(id).append(", ")
        .append("Date: ").append(date).append(", ")
        .append("Trip: ").append(trip);
    return sb.toString();
  }

}
