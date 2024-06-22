package com.vuelosjanbi.crewRole.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.crewRole.application.ports.out.CrewRoleRepositoryport;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;

public class MySQLCrewRoleRepository implements CrewRoleRepositoryport {

  @Override
  public CrewRole save(CrewRole crewRole) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public Optional<CrewRole> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public List<CrewRole> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

}
