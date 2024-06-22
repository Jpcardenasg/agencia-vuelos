package com.vuelosjanbi.planeModel.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeModel.domain.models.PlaneModel;

public interface PlaneModelRepositoryPort {

    PlaneModel save(PlaneModel planeModel);

    void deleteById(Long id);

    Optional<PlaneModel> findById(Long id);

    Optional<PlaneModel> findByName(String name);

    List<PlaneModel> findByPlaneManufacturerName(String manufacturerName);

    List<PlaneModel> findAll();
}
