package com.vuelosjanbi.planeRevision.application.ports.out;

import com.vuelosjanbi.planeRevision.domain.models.PlaneRevision;

import java.util.List;
import java.util.Optional;

public interface PlaneRevisionRepositoryPort {
  PlaneRevision save(PlaneRevision planeRevision);

  void deleteById(Long id);

  Optional<PlaneRevision> findById(Long id);

  List<PlaneRevision> findByPlaneId(Long planeId);

  List<PlaneRevision> findAll();
}
