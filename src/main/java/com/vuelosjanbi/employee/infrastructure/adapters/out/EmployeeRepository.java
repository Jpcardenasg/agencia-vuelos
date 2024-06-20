package com.vuelosjanbi.employee.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.employee.domain.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryPort {

}
