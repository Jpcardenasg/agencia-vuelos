package com.vuelosjanbi.planeRevisionEmployee.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.planeRevisionEmployee.application.ports.out.PlaneRevisionEmployeeRepositoryPort;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlanRevisionEmployee;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.RevisionEmployeeId;

@Repository
public interface PlaneRevisionEmployeeRepository extends JpaRepository<PlanRevisionEmployee, RevisionEmployeeId>,
        PlaneRevisionEmployeeRepositoryPort {

}
