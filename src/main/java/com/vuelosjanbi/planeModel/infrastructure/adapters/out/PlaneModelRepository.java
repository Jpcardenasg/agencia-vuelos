package com.vuelosjanbi.planeModel.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.planeModel.application.ports.out.PlaneModelRepositoryPort;
import com.vuelosjanbi.planeModel.domain.models.PlaneModel;

public interface PlaneModelRepository extends JpaRepository<PlaneModel, Long>, PlaneModelRepositoryPort {

}
