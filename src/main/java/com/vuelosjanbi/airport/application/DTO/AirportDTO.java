package com.vuelosjanbi.airport.application.DTO;

import com.vuelosjanbi.city.application.DTO.CityDTO;

public class AirportDTO {
  private Long id;
  private String name;
  private CityDTO city;

  public AirportDTO() {
  }

  public AirportDTO(Long id, String name, CityDTO city) {
    this.id = id;
    this.name = name;
    this.city = city;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CityDTO getCity() {
    return city;
  }

  public void setCity(CityDTO city) {
    this.city = city;
  }

}
