package com.vuelosjanbi.tripBookingDetail.application.ports.out;

import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;
import java.util.Optional;
import java.util.List;

public interface TripBookingDetailRepositoryPort {

  TripBookingDetail save(TripBookingDetail tripBookingDetail);

  void deleteById(Long id);

  Optional<TripBookingDetail> findByCustomer(Customer customer);

  Optional<TripBookingDetail> findByCustomerId(String customerId);

  Optional<TripBookingDetail> findById(Long id);

  List<TripBookingDetail> findAll();

  Optional<TripBookingDetail> findByTripBookingId(Long tripBookingId);

  Optional<TripBookingDetail> findByTripBookingIdAndCustomerId(Long tripBookingId, String customerId);

  Optional<TripBookingDetail> findByTripBookingIdAndCustomer(Long tripBookingId, String customerId);

  Optional<TripBookingDetail> findByTripBookingIdAndCustomerIdAndFlightFareId(Long tripBookingId, String customerId,
      Long flightFareId);

}