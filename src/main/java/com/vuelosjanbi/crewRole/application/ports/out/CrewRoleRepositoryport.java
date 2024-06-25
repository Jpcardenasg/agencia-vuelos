package com.vuelosjanbi.crewRole.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.crewRole.domain.models.CrewRole;

public interface CrewRoleRepositoryport {
  CrewRole save(CrewRole crewRole);

  void deleteById(Long id);

  Optional<CrewRole> findById(String id);

  Optional<CrewRole> findByName(String name);

  List<CrewRole> findAll();
}
