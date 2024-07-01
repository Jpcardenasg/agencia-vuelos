package com.vuelosjanbi.employee.domain.models;

import java.sql.Date;

import com.vuelosjanbi.airline.domain.models.Airline;
import com.vuelosjanbi.airport.domain.models.Airport;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Employee {
  @Id
  private String id;
  private String name;
  @Temporal(TemporalType.DATE)
  private Date entryDate;

  @ManyToOne
  private CrewRole rol;

  @ManyToOne
  private Airline airline;

  @ManyToOne
  private Airport airport;

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public Date getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName()).append(" ")
        .append("\n\tId: ").append(id)
        .append(",\n\tName: ").append(name)
        .append(",\n\tEntry Date: ").append(entryDate)
        .append(",\n\tRol: ").append(rol != null ? rol.getName() : "null")
        .append(",\n\tAirline: ").append(airline != null ? airline.getName() : "null")
        .append(",\n\tAirport: ").append(airport != null ? airport.getName() : "null");
    return sb.toString();
  }

}
