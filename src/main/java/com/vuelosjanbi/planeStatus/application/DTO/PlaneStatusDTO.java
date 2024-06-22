package com.vuelosjanbi.planeStatus.application.DTO;

import java.util.List;

import com.vuelosjanbi.plane.application.DTO.PlaneDTO;

public class PlaneStatusDTO {
  private Long id;
  private String name;
  List<PlaneDTO> planes;

  public PlaneStatusDTO() {
  }

  public PlaneStatusDTO(Long id, String name, List<PlaneDTO> planes) {
    this.id = id;
    this.name = name;
    this.planes = planes;
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

  public List<PlaneDTO> getPlanes() {
    return planes;
  }

  public void setPlanes(List<PlaneDTO> planes) {
    this.planes = planes;
  }

}
