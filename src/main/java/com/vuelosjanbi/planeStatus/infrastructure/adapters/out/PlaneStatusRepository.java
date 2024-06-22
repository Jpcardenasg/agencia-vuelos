package com.vuelosjanbi.planeStatus.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.planeStatus.application.ports.out.PlaneStatusRepositoryPort;
import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;

@Repository
public interface PlaneStatusRepository extends JpaRepository<PlaneStatus, Long>, PlaneStatusRepositoryPort {

}
