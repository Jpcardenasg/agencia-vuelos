package com.vuelosjanbi.planeRevisionEmployee.application.ports.out;

import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlaneRevisionEmployee;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.RevisionEmployeeId;

import java.util.Optional;
import java.util.List;

public interface PlaneRevisionEmployeeRepositoryPort {

  PlaneRevisionEmployee save(PlaneRevisionEmployee planeRevisionEmployee);

  Optional<PlaneRevisionEmployee> findById(RevisionEmployeeId id);

  void deleteById(RevisionEmployeeId id);

  PlaneRevisionEmployee findByEmployeeId(String employeeId);

  List<PlaneRevisionEmployee> findAll();

}
