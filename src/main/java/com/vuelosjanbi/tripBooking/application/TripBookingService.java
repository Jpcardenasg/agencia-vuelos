package com.vuelosjanbi.tripBooking.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.tripBooking.application.ports.out.TripBookingRepositoryPort;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

@Service
public class TripBookingService {

  @Autowired
  private TripBookingRepositoryPort tripBookingRepositoryPort;

  public TripBookingService() {
  }

  public TripBookingService(TripBookingRepositoryPort tripBookingRepositoryPort) {
    this.tripBookingRepositoryPort = tripBookingRepositoryPort;
  }

  public TripBooking createTripBooking(TripBooking tripBooking) {
    return tripBookingRepositoryPort.save(tripBooking);
  }

  public TripBooking getTripBooking(Long id) {
    return tripBookingRepositoryPort.findById(id).orElse(null);
  }

  public void deleteTripBooking(Long id) {
    tripBookingRepositoryPort.deleteById(id);
  }

  public TripBooking updateTripBooking(TripBooking tripBooking) {
    return tripBookingRepositoryPort.save(tripBooking);
  }

  public List<TripBooking> getAllTripBookings() {
    return tripBookingRepositoryPort.findAll();
  }

}
