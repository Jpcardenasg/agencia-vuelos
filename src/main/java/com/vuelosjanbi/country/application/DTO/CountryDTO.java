package com.vuelosjanbi.country.application.DTO;

import java.util.List;

import com.vuelosjanbi.city.application.DTO.CityDTO;

public class CountryDTO {

  private Long id;
  private String name;

  List<CityDTO> cities;

  public CountryDTO() {
  }

  public CountryDTO(String name) {
    this.name = name;
  }

  public CountryDTO(Long id, String name, List<CityDTO> cities) {
    this.id = id;
    this.name = name;
    this.cities = cities;
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

  public List<CityDTO> getCities() {
    return cities;
  }

  public void setCities(List<CityDTO> cities) {
    this.cities = cities;
  }

}
