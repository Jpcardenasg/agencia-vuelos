package com.vuelosjanbi.planeModel.application.DTO;

import com.vuelosjanbi.planeManufacturer.application.DTO.PlaneManufacturerDTO;

public class PlaneModelDTO {
  private Long id;
  private String name;

  private PlaneManufacturerDTO planeManufacturer;

  public PlaneModelDTO() {
  }

  public PlaneModelDTO(Long id, String name, PlaneManufacturerDTO planeManufacturer) {
    this.id = id;
    this.name = name;
    this.planeManufacturer = planeManufacturer;
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

  public PlaneManufacturerDTO getPlaneManufacturer() {
    return planeManufacturer;
  }

  public void setPlaneManufacturer(PlaneManufacturerDTO planeManufacturer) {
    this.planeManufacturer = planeManufacturer;
  }

}
