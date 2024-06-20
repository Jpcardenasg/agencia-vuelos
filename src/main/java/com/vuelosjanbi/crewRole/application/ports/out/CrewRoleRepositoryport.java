package com.vuelosjanbi.crewRole.application.ports.out;

import java.util.List;

import com.vuelosjanbi.crewRole.domain.models.CrewRole;

public interface CrewRoleRepositoryport {
  CrewRole save(CrewRole crewRole);

  CrewRole findById(Long id);

  void deleteById(Long id);

  List<CrewRole> findAll();
}
