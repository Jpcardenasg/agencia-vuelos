package com.vuelosjanbi.planeRevisionEmployee.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.planeRevisionEmployee.application.ports.out.PlaneRevisionEmployeeRepositoryPort;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlaneRevisionEmployee;

public interface PlaneRevisionEmployeeRepository extends JpaRepository<PlaneRevisionEmployee, Long>,
    PlaneRevisionEmployeeRepositoryPort {

}
