package com.vuelosjanbi.planeManufacturer.application.DTO;

import java.util.List;

import com.vuelosjanbi.planeModel.application.DTO.PlaneModelDTO;

public class PlaneManufacturerDTO {
  private Long id;
  private String name;

  List<PlaneModelDTO> planeModels;

  public PlaneManufacturerDTO() {
  }

  public PlaneManufacturerDTO(Long id, String name, List<PlaneModelDTO> planeModels) {
    this.id = id;
    this.name = name;
    this.planeModels = planeModels;
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

  public List<PlaneModelDTO> getPlaneModels() {
    return planeModels;
  }

  public void setPlaneModels(List<PlaneModelDTO> planeModels) {
    this.planeModels = planeModels;
  }

}
