package com.vuelosjanbi.tripBookingDetail.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.tripBookingDetail.application.ports.out.TripBookingDetailRepositoryPort;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;

public class MySQLTripBookingDetailRepository implements TripBookingDetailRepositoryPort {

  @Override
  public TripBookingDetail save(TripBookingDetail tripBookingDetail) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public Optional<TripBookingDetail> findByCustomer(Customer customer) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByCustomer'");
  }

  @Override
  public Optional<TripBookingDetail> findByCustomerId(Customer customer) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByCustomerId'");
  }

  @Override
  public Optional<TripBookingDetail> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public List<TripBookingDetail> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

}
