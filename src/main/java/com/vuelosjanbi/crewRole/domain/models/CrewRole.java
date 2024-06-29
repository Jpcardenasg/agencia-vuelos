package com.vuelosjanbi.crewRole.domain.models;

import java.util.List;

import com.vuelosjanbi.employee.domain.models.Employee;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CrewRole implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @OneToMany(mappedBy = "rol")
  List<Employee> employees;

  public CrewRole() {
  }

  public CrewRole(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public CrewRole(String name) {
    this.name = name;
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

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public String toString() {
    return "CrewRole [id=" + id + ", name=" + name + "]";
  }

}
