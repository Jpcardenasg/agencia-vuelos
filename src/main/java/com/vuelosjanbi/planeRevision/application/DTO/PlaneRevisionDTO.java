package com.vuelosjanbi.planeRevision.application.DTO;

import java.sql.Date;

import com.vuelosjanbi.plane.application.DTO.PlaneDTO;

public class PlaneRevisionDTO {
  private Long id;

  private Date revisionDate;

  private PlaneDTO plane;

  public PlaneRevisionDTO() {
  }

  public PlaneRevisionDTO(Long id, Date revisionDate, PlaneDTO plane) {
    this.id = id;
    this.revisionDate = revisionDate;
    this.plane = plane;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getRevisionDate() {
    return revisionDate;
  }

  public void setRevisionDate(Date revisionDate) {
    this.revisionDate = revisionDate;
  }

  public PlaneDTO getPlane() {
    return plane;
  }

  public void setPlane(PlaneDTO plane) {
    this.plane = plane;
  }

}
