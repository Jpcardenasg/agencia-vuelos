package com.vuelosjanbi.tripBooking.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuelosjanbi.tripBooking.application.ports.out.TripBookingRepositoryPort;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

public interface TripBookingRepository extends JpaRepository<TripBooking, Long>, TripBookingRepositoryPort {

}
