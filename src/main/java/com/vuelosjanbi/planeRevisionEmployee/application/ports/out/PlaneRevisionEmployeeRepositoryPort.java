package com.vuelosjanbi.planeRevisionEmployee.application.ports.out;

import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlanRevisionEmployee;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.RevisionEmployeeId;

import java.util.Optional;
import java.util.List;

public interface PlaneRevisionEmployeeRepositoryPort {

  PlanRevisionEmployee save(PlanRevisionEmployee planeRevisionEmployee);

  Optional<PlanRevisionEmployee> findById(RevisionEmployeeId id);

  void deleteById(RevisionEmployeeId id);

  PlanRevisionEmployee findByEmployeeId(Long employeeId);

  List<PlanRevisionEmployee> findAll();

}
