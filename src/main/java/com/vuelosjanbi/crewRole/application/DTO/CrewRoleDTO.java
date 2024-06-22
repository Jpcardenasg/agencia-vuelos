package com.vuelosjanbi.crewRole.application.DTO;

import java.util.List;

import com.vuelosjanbi.employee.application.DTO.EmployeeDTO;

public class CrewRoleDTO {
  private Long id;
  private String name;

  List<EmployeeDTO> employees;

  public CrewRoleDTO() {
  }

  public CrewRoleDTO(Long id, String name, List<EmployeeDTO> employees) {
    this.id = id;
    this.name = name;
    this.employees = employees;
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

  public List<EmployeeDTO> getEmployees() {
    return employees;
  }

  public void setEmployees(List<EmployeeDTO> employees) {
    this.employees = employees;
  }

}
