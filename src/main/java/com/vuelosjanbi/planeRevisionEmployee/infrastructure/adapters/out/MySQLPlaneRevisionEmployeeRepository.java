package com.vuelosjanbi.planeRevisionEmployee.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeRevisionEmployee.application.ports.out.PlaneRevisionEmployeeRepositoryPort;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlaneRevisionEmployee;

public class MySQLPlaneRevisionEmployeeRepository implements PlaneRevisionEmployeeRepositoryPort {

  @Override
  public PlaneRevisionEmployee save(PlaneRevisionEmployee planeRevisionEmployee) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public Optional<PlaneRevisionEmployee> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public PlaneRevisionEmployee findByEmployeeId(Long employeeId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByEmployeeId'");
  }

  @Override
  public List<PlaneRevisionEmployee> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

}
