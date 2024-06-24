package com.vuelosjanbi.employee.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.employee.domain.models.Employee;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepositoryPort employeeRepositoryPort;

  public EmployeeService(EmployeeRepositoryPort employeeRepositoryPort) {
    this.employeeRepositoryPort = employeeRepositoryPort;
  }

  public Employee createEmployee(Employee employee) {
    return employeeRepositoryPort.save(employee);
  }

  public Employee updateEmployee(Employee employee) {
    return employeeRepositoryPort.save(employee);
  }

  public void deleteEmployeeById(String id) {
    employeeRepositoryPort.deleteById(id);
  }

  public Optional<Employee> getEmployeeById(String id) {
    return employeeRepositoryPort.findById(id);
  }

  public List<Employee> getEmployeesByRol(Long roleId) {
    return employeeRepositoryPort.findByRolId(roleId);
  }

  public List<Employee> getAllEmployees() {
    return employeeRepositoryPort.findAll();
  }

}
