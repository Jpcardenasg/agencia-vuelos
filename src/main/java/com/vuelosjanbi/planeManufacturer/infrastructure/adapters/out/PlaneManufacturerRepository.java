package com.vuelosjanbi.planeManufacturer.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.planeManufacturer.application.ports.out.PlaneManufacturerRepositoryPort;
import com.vuelosjanbi.planeManufacturer.domain.models.PlaneManufacturer;

public interface PlaneManufacturerRepository
        extends JpaRepository<PlaneManufacturer, Long>, PlaneManufacturerRepositoryPort {

}
