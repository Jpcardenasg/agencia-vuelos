package com.vuelosjanbi.airportGate.domain.models;

import com.vuelosjanbi.airport.domain.models.Airport;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AirportGate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @ManyToOne
  Airport airport;

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

  public Airport getAirport() {
    return airport;
  }

  public void setAirport(Airport airport) {
    this.airport = airport;
  }

}
