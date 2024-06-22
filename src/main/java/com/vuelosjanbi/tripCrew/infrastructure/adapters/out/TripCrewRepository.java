package com.vuelosjanbi.tripCrew.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.tripCrew.application.ports.out.TripCrewRepositoryPort;
import com.vuelosjanbi.tripCrew.domain.models.TripCrew;
import com.vuelosjanbi.tripCrew.domain.models.TripCrewId;

public interface TripCrewRepository extends JpaRepository<TripCrew, TripCrewId>, TripCrewRepositoryPort {

}
