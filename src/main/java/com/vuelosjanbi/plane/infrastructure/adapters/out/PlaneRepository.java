package com.vuelosjanbi.plane.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.plane.application.ports.out.PlaneRepositoryPort;
import com.vuelosjanbi.plane.domain.models.Plane;

public interface PlaneRepository extends JpaRepository<Plane, Long>, PlaneRepositoryPort {

}
