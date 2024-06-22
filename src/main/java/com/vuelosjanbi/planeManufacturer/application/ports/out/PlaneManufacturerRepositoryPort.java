package com.vuelosjanbi.planeManufacturer.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeManufacturer.domain.models.PlaneManufacturer;

public interface PlaneManufacturerRepositoryPort {

    PlaneManufacturer save(PlaneManufacturer planeManufacturer);

    void deleteById(Long id);

    Optional<PlaneManufacturer> findById(Long id);

    Optional<PlaneManufacturer> findByName(String name);

    List<PlaneManufacturer> findAll();

}
