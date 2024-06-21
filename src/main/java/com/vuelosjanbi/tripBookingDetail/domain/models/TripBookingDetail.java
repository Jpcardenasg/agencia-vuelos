package com.vuelosjanbi.tripBookingDetail.domain.models;

import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.flightFare.domain.models.FlightFare;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TripBookingDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private TripBooking tripBooking;

  @ManyToOne
  private Customer customer;

  @ManyToOne
  private FlightFare flightFare;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TripBooking getTripBooking() {
    return tripBooking;
  }

  public void setTripBooking(TripBooking tripBooking) {
    this.tripBooking = tripBooking;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public FlightFare getFlightFare() {
    return flightFare;
  }

  public void setFlightFare(FlightFare flightFare) {
    this.flightFare = flightFare;
  }

}
