package com.vuelosjanbi.flightFare.application.DTO;

import java.util.List;

import com.vuelosjanbi.tripBookingDetail.application.DTO.TripBookingDetailDTO;

public class FlightFareDTO {
  private Long id;
  private String description;
  private String details;
  private Double value;

  List<TripBookingDetailDTO> tripBookingDetails;

  public FlightFareDTO() {
  }

  public FlightFareDTO(Long id, String description, String details, Double value,
      List<TripBookingDetailDTO> tripBookingDetails) {
    this.id = id;
    this.description = description;
    this.details = details;
    this.value = value;
    this.tripBookingDetails = tripBookingDetails;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public List<TripBookingDetailDTO> getTripBookingDetails() {
    return tripBookingDetails;
  }

  public void setTripBookingDetails(List<TripBookingDetailDTO> tripBookingDetails) {
    this.tripBookingDetails = tripBookingDetails;
  }

}
