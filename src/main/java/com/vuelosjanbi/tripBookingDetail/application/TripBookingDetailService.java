package com.vuelosjanbi.tripBookingDetail.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.tripBookingDetail.application.ports.out.TripBookingDetailRepositoryPort;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;

@Service
public class TripBookingDetailService {

  @Autowired
  private TripBookingDetailRepositoryPort tripBookingDetailRepositoryPort;

  public TripBookingDetailService() {
  }

  public TripBookingDetailService(TripBookingDetailRepositoryPort tripBookingDetailRepositoryPort) {
    this.tripBookingDetailRepositoryPort = tripBookingDetailRepositoryPort;
  }

  public TripBookingDetail createTripBookingDetail(TripBookingDetail tripBookingDetail) {
    return tripBookingDetailRepositoryPort.save(tripBookingDetail);
  }

  public TripBookingDetail getTripBookingDetail(Long id) {
    return tripBookingDetailRepositoryPort.findById(id).orElse(null);
  }

  public void deleteTripBookingDetail(Long id) {
    tripBookingDetailRepositoryPort.deleteById(id);
  }

  public TripBookingDetail updateTripBookingDetail(TripBookingDetail tripBookingDetail) {
    return tripBookingDetailRepositoryPort.save(tripBookingDetail);
  }

  public List<TripBookingDetail> getAllTripBookingDetails() {
    return tripBookingDetailRepositoryPort.findAll();
  }

  public List<TripBookingDetail> getTripBookingDetailsByTripBookingId(Long tripBookingId) {
    return tripBookingDetailRepositoryPort.findByTripBookingId(tripBookingId);
  }

  public List<TripBookingDetail> getTripBookingDetailsByTripId(Long tripId) {
    return tripBookingDetailRepositoryPort.findByTripBookingId(tripId);
  }

  public List<TripBookingDetail> getTripBookingDetailsByCustomerId(String customerId) {
    return tripBookingDetailRepositoryPort.findByCustomerId(customerId);
  }

}
