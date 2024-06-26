package com.vuelosjanbi.planeRevisionEmployee.domain.models;

import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.planeRevision.domain.models.PlaneRevision;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class PlaneRevisionEmployee {
  @EmbeddedId
  private RevisionEmployeeId id;

  @ManyToOne
  @MapsId("employeeId")
  private Employee employee;

  @ManyToOne
  @MapsId("planeRevisionId")
  private PlaneRevision planRevision;

  public RevisionEmployeeId getId() {
    return id;
  }

  public void setId(RevisionEmployeeId id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public PlaneRevision getPlanRevision() {
    return planRevision;
  }

  public void setPlanRevision(PlaneRevision planeRevision) {
    this.planRevision = planeRevision;
  }

}
