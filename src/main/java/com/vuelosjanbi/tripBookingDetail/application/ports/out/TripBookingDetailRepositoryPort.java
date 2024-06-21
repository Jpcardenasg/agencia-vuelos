package com.vuelosjanbi.tripBookingDetail.application.ports.out;

import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;
import java.util.Optional;
import java.util.List;

public interface TripBookingDetailRepositoryPort {

  TripBookingDetail save(TripBookingDetail tripBookingDetail);

  void deleteById(Long id);

  Optional<TripBookingDetail> findByCustomer(Customer customer);

  Optional<TripBookingDetail> findByCustomerId(Customer customer);

  Optional<TripBookingDetail> findById(Long id);

  List<TripBookingDetail> findAll();

}