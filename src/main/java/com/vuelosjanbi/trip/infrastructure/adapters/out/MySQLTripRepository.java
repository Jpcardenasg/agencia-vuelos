package com.vuelosjanbi.trip.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.trip.application.ports.out.TripRepositoryPort;
import com.vuelosjanbi.trip.domain.models.Trip;

public class MySQLTripRepository implements TripRepositoryPort {

  @Override
  public Trip save(Trip trip) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public List<Trip> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public Optional<Trip> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

}
