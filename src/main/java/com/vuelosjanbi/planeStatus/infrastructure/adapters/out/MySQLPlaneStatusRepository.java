package com.vuelosjanbi.planeStatus.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeStatus.application.ports.out.PlaneStatusRepositoryPort;
import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;

public class MySQLPlaneStatusRepository implements PlaneStatusRepositoryPort {

  @Override
  public PlaneStatus save(PlaneStatus planeStatus) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public Optional<PlaneStatus> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public List<PlaneStatus> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

}
