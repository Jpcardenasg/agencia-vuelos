package com.vuelosjanbi.tripBooking.application.ports.out;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

public interface TripBookingRepositoryPort {

  TripBooking save(TripBooking tripBooking);

  void deleteById(Long id);

  Optional<TripBooking> findById(Long id);

  List<TripBooking> findAll();

  public List<TripBooking> findByTripId(Long tripId);

  public List<TripBooking> findByDate(Date date);

  public List<TripBooking> findBytripBookingDetailsCustomer(Customer customer);

}
