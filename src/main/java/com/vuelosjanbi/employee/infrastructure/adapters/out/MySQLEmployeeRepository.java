package com.vuelosjanbi.employee.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.crewRole.domain.models.CrewRole;
import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.employee.domain.models.Employee;

public class MySQLEmployeeRepository implements EmployeeRepositoryPort {

  @Override
  public Employee save(Employee employee) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public Optional<Employee> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public List<Employee> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public List<Employee> findByRolId(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByRolId'");
  }

  @Override
  public List<Employee> findByRol(CrewRole Rol) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByRol'");
  }

}
