package com.vuelosjanbi.trip.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.trip.application.ports.out.TripRepositoryPort;
import com.vuelosjanbi.trip.domain.models.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long>, TripRepositoryPort {

}
