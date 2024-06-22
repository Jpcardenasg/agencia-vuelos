package com.vuelosjanbi.airportGate.application.DTO;

import com.vuelosjanbi.airport.application.DTO.AirportDTO;

public class AiportGateDTO {
  private Long id;
  private String gate;
  private AirportDTO airport;

  public AiportGateDTO() {
  }

  public AiportGateDTO(Long id, String gate, AirportDTO airport) {
    this.id = id;
    this.gate = gate;
    this.airport = airport;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGate() {
    return gate;
  }

  public void setGate(String gate) {
    this.gate = gate;
  }

  public AirportDTO getAirport() {
    return airport;
  }

  public void setAirport(AirportDTO airport) {
    this.airport = airport;
  }

}
