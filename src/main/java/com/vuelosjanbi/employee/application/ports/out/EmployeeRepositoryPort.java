package com.vuelosjanbi.employee.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.employee.domain.models.Employee;

public interface EmployeeRepositoryPort {

  Employee save(Employee employee);

  Optional<Employee> findById(String id);

  void deleteById(String id);

  List<Employee> findAll();

  List<Employee> findByRolId(Long id);

  List<Employee> findEmployeesByAirlineId(Long airlineId);

}
