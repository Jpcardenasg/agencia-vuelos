package com.vuelosjanbi.trip.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.trip.application.ports.out.TripRepositoryPort;
import com.vuelosjanbi.trip.domain.models.Trip;

public interface TripRepository extends JpaRepository<Trip, Long>, TripRepositoryPort {

}
