package com.vuelosjanbi.tripBooking.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.tripBooking.application.ports.out.TripBookingRepositoryPort;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Long>, TripBookingRepositoryPort {

}
