package com.vuelosjanbi.planeRevision.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.planeRevision.application.ports.out.PlaneRevisionRepositoryPort;
import com.vuelosjanbi.planeRevision.domain.models.PlaneRevision;

public interface PlaneRevisionRepository extends JpaRepository<PlaneRevision, Long>, PlaneRevisionRepositoryPort {

}
