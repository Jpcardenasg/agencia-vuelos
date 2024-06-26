package com.vuelosjanbi.tripBooking.application.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

public interface TripBookingRepositoryPort {

  TripBooking save(TripBooking tripBooking);

  void deleteById(Long id);

  Optional<TripBooking> findById(Long id);

  List<TripBooking> findAll();

  public List<TripBooking> findByTripId(Long tripId);

  public List<TripBooking> findByDate(String date);

  public List<TripBooking> findByCustomerId(String customerId);

}
