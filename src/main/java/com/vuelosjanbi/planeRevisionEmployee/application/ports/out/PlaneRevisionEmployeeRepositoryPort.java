package com.vuelosjanbi.planeRevisionEmployee.application.ports.out;

import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlaneRevisionEmployee;

import java.util.Optional;
import java.util.List;

public interface PlaneRevisionEmployeeRepositoryPort {

  PlaneRevisionEmployee save(PlaneRevisionEmployee planeRevisionEmployee);

  Optional<PlaneRevisionEmployee> findById(Long id);

  void deleteById(Long id);

  PlaneRevisionEmployee findByEmployeeId(Long employeeId);

  List<PlaneRevisionEmployee> findAll();

}
