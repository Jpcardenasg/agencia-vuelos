package com.vuelosjanbi.employee.domain.models;

import com.vuelosjanbi.airline.domain.models.Airline;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;

  @ManyToOne
  private CrewRole rol;

  @ManyToOne
  private Airline airline;

  @ManyToOne
  private Airport airport;

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

  public CrewRole getRol() {
    return rol;
  }

  public void setRol(CrewRole crewRole) {
    this.rol = crewRole;
  }

  public Airline getAirline() {
    return airline;
  }

  public void setAirline(Airline airline) {
    this.airline = airline;
  }

  public Airport getAirport() {
    return airport;
  }

  public void setAirport(Airport airport) {
    this.airport = airport;
  }

}
