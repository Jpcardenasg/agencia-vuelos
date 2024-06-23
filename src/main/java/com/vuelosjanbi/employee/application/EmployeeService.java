package com.vuelosjanbi.employee.application;

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

  public EmployeeService() {
  }

  public Employee getEmployeeById(Long id) {
    return employeeRepositoryPort.findById(id).orElse(null);
  }

  public Employee createEmployee(Employee employee) {
    return employeeRepositoryPort.save(employee);
  }

  public void deleteEmployeeById(Long id) {
    employeeRepositoryPort.deleteById(id);
  }

  public Employee updateEmployee(Employee employee) {
    return employeeRepositoryPort.save(employee);
  }

}
