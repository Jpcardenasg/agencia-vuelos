package com.vuelosjanbi.planeRevisionEmployee.domain.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class RevisionEmployeeId {
  private Long employeeId;
  private Long planeRevisionId;

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Long getPlaneRevisionId() {
    return planeRevisionId;
  }

  public void setPlaneRevisionId(Long planeRevisionId) {
    this.planeRevisionId = planeRevisionId;
  }
}
