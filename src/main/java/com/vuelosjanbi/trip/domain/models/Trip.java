package com.vuelosjanbi.trip.domain.models;

import java.time.LocalDate;

import com.vuelosjanbi.employee.domain.models.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate trip_date;

  private double price_trip;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getTrip_date() {
    return trip_date;
  }

  public void setTrip_date(LocalDate trip_date) {
    this.trip_date = trip_date;
  }

  public double getPrice_trip() {
    return price_trip;
  }

  public void setPrice_trip(double price_trip) {
    this.price_trip = price_trip;
  }

}
