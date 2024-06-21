package com.vuelosjanbi.planeStatus.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;

public interface PlaneStatusRepositoryPort {

  PlaneStatus save(PlaneStatus planeStatus);

  Optional<PlaneStatus> findById(Long id);

  void deleteById(Long id);

  List<PlaneStatus> findAll();

}
