package com.vuelosjanbi.planeRevisionEmployee.domain.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class RevisionEmployeeId implements Serializable {
  private String employeeId;
  private Long planeRevisionId;

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public Long getPlaneRevisionId() {
    return planeRevisionId;
  }

  public void setPlaneRevisionId(Long planeRevisionId) {
    this.planeRevisionId = planeRevisionId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(employeeId, planeRevisionId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    RevisionEmployeeId that = (RevisionEmployeeId) obj;
    return Objects.equals(employeeId, that.employeeId) && Objects.equals(planeRevisionId, that.planeRevisionId);
  }

}
