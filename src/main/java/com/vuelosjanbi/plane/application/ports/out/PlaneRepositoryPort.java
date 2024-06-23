package com.vuelosjanbi.plane.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.plane.domain.models.Plane;

public interface PlaneRepositoryPort {
    Plane save(Plane plane);

    void deleteById(Long planeId);

    Optional<Plane> findById(Long planeId);

    List<Plane> findAll();

}
