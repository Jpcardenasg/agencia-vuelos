package com.vuelosjanbi.employee.application.DTO;

import com.vuelosjanbi.airline.application.DTO.AirlineDTO;
import com.vuelosjanbi.airport.application.DTO.AirportDTO;
import com.vuelosjanbi.crewRole.application.DTO.CrewRoleDTO;

public class EmployeeDTO {
  private Long id;
  private String name;
  private CrewRoleDTO rol;
  private AirlineDTO airline;
  private AirportDTO airport;

  public EmployeeDTO() {
  }

  public EmployeeDTO(Long id, String name, CrewRoleDTO rol, AirlineDTO airline, AirportDTO airport) {
    this.id = id;
    this.name = name;
    this.rol = rol;
    this.airline = airline;
    this.airport = airport;
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

  public CrewRoleDTO getRol() {
    return rol;
  }

  public void setRol(CrewRoleDTO rol) {
    this.rol = rol;
  }

  public AirlineDTO getAirline() {
    return airline;
  }

  public void setAirline(AirlineDTO airline) {
    this.airline = airline;
  }

  public AirportDTO getAirport() {
    return airport;
  }

  public void setAirport(AirportDTO airport) {
    this.airport = airport;
  }

}
