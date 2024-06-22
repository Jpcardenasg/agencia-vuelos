package com.vuelosjanbi.trip.domain.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date tripDate;

  private double tripPrice;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getTripPrice() {
    return tripPrice;
  }

  public void setTripPrice(double priceTrip) {
    this.tripPrice = priceTrip;
  }

  public Date getTripDate() {
    return tripDate;
  }

  public void setTripDate(Date tripDate) {
    this.tripDate = tripDate;
  }

}
