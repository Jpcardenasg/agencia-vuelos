package com.vuelosjanbi.employee.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.employee.domain.models.Employee;

public interface EmployeeRepositoryPort {
  Employee save(Employee employee);

  Optional<Employee> findById(Long id);

  void deleteById(Long id);

  List<Employee> findAll();

  List<Employee> findByCrewRole(Long crewRoleId);
}
