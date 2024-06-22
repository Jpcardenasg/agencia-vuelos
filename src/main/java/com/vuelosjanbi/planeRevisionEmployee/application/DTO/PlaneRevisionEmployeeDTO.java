package com.vuelosjanbi.planeRevisionEmployee.application.DTO;

import com.vuelosjanbi.employee.application.DTO.EmployeeDTO;
import com.vuelosjanbi.planeRevision.application.DTO.PlaneRevisionDTO;

public class PlaneRevisionEmployeeDTO {
  private Long employeeId;
  private Long planeRevisionId;
  private EmployeeDTO employee;
  private PlaneRevisionDTO planeRevision;

  public PlaneRevisionEmployeeDTO() {
  }

  public PlaneRevisionEmployeeDTO(Long employeeId, Long planeRevisionId, EmployeeDTO employee,
      PlaneRevisionDTO planeRevision) {
    this.employeeId = employeeId;
    this.planeRevisionId = planeRevisionId;
    this.employee = employee;
    this.planeRevision = planeRevision;
  }

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

  public EmployeeDTO getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeDTO employee) {
    this.employee = employee;
  }

  public PlaneRevisionDTO getPlaneRevision() {
    return planeRevision;
  }

  public void setPlaneRevision(PlaneRevisionDTO planeRevision) {
    this.planeRevision = planeRevision;
  }

}
