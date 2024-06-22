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
  private PlaneRevision planeRevision;

}
