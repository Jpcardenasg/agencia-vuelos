package com.vuelosjanbi.tripBookingDetail.application.DTO;

import com.vuelosjanbi.customer.application.DTO.CustomerDTO;
import com.vuelosjanbi.flightFare.application.DTO.FlightFareDTO;
import com.vuelosjanbi.tripBooking.application.DTO.TripBookingDTO;

public class TripBookingDetailDTO {
  private Long id;
  private TripBookingDTO tripBooking;
  private CustomerDTO customer;
  private FlightFareDTO flightFare;

  public TripBookingDetailDTO() {
  }

  public TripBookingDetailDTO(Long id, TripBookingDTO tripBooking, CustomerDTO customer, FlightFareDTO flightFare) {
    this.id = id;
    this.tripBooking = tripBooking;
    this.customer = customer;
    this.flightFare = flightFare;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TripBookingDTO getTripBooking() {
    return tripBooking;
  }

  public void setTripBooking(TripBookingDTO tripBooking) {
    this.tripBooking = tripBooking;
  }

  public CustomerDTO getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerDTO customer) {
    this.customer = customer;
  }

  public FlightFareDTO getFlightFare() {
    return flightFare;
  }

  public void setFlightFare(FlightFareDTO flightFare) {
    this.flightFare = flightFare;
  }

}
