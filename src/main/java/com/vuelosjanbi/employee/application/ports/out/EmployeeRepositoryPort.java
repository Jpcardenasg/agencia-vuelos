package com.vuelosjanbi.employee.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.crewRole.domain.models.CrewRole;
import com.vuelosjanbi.employee.domain.models.Employee;

public interface EmployeeRepositoryPort {
  Employee save(Employee employee);

  Optional<Employee> findById(Long id);

  void deleteById(Long id);

  List<Employee> findAll();

  List<Employee> findByRolId(Long id);

  List<Employee> findByRol(CrewRole Rol);

  List<Employee> findEmployeesByAirlineId(Long airlineId);

}
