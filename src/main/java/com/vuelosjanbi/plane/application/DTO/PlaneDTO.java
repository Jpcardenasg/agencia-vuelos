package com.vuelosjanbi.plane.application.DTO;

import java.sql.Date;

import com.vuelosjanbi.planeModel.application.DTO.PlaneModelDTO;
import com.vuelosjanbi.trip.application.DTO.TripDTO;

public class PlaneDTO {
  private Long id;
  private String plate;
  private Integer capacity;
  private Date fabricationDate;
  private TripDTO status;
  private PlaneModelDTO model;

  public PlaneDTO() {
  }

  public PlaneDTO(Long id, String plate, Integer capacity, Date fabricationDate, TripDTO status,
      PlaneModelDTO model) {
    this.id = id;
    this.plate = plate;
    this.capacity = capacity;
    this.fabricationDate = fabricationDate;
    this.status = status;
    this.model = model;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public Date getFabricationDate() {
    return fabricationDate;
  }

  public void setFabricationDate(Date fabricationDate) {
    this.fabricationDate = fabricationDate;
  }

  public TripDTO getStatus() {
    return status;
  }

  public void setStatus(TripDTO status) {
    this.status = status;
  }

  public PlaneModelDTO getModel() {
    return model;
  }

  public void setModel(PlaneModelDTO model) {
    this.model = model;
  }

}
