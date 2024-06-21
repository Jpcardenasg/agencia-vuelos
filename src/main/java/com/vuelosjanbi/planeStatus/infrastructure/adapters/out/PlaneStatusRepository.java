package com.vuelosjanbi.planeStatus.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;

public interface PlaneStatusRepository extends JpaRepository<PlaneStatus, Long> {

}
