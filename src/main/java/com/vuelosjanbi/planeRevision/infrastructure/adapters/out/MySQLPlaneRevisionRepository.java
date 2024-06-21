package com.vuelosjanbi.planeRevision.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeRevision.application.ports.out.PlaneRevisionRepositoryPort;
import com.vuelosjanbi.planeRevision.domain.models.PlaneRevision;

public class MySQLPlaneRevisionRepository implements PlaneRevisionRepositoryPort {

  @Override
  public PlaneRevision save(PlaneRevision planeRevision) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public Optional<PlaneRevision> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public Optional<PlaneRevision> findByPlaneId(Long planeId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByPlaneId'");
  }

  @Override
  public List<PlaneRevision> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

}
