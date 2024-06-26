package com.vuelosjanbi.tripCrew.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.tripCrew.application.ports.out.TripCrewRepositoryPort;
import com.vuelosjanbi.tripCrew.domain.models.TripCrew;
import com.vuelosjanbi.tripCrew.domain.models.TripCrewId;

@Repository
public interface TripCrewRepository extends JpaRepository<TripCrew, TripCrewId>, TripCrewRepositoryPort {

}
