package com.vuelosjanbi.city.application.DTO;

import java.util.List;

import com.vuelosjanbi.airport.application.DTO.AirportDTO;
import com.vuelosjanbi.country.application.DTO.CountryDTO;

public class CityDTO {

  private Long id;
  private String name;
  private CountryDTO country;
  private List<AirportDTO> airports;

  public CityDTO() {
  }

  public CityDTO(Long id, String name, CountryDTO country, List<AirportDTO> airports) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.airports = airports;
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

  public CountryDTO getCountry() {
    return country;
  }

  public void setCountry(CountryDTO country) {
    this.country = country;
  }

  public List<AirportDTO> getAirports() {
    return airports;
  }

  public void setAirports(List<AirportDTO> airports) {
    this.airports = airports;
  }

}
