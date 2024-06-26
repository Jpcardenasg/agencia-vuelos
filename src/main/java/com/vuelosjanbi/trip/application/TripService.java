package com.vuelosjanbi.trip.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.trip.application.ports.out.TripRepositoryPort;
import com.vuelosjanbi.trip.domain.models.Trip;

import java.util.List;

@Service
public class TripService {

  @Autowired
  private TripRepositoryPort tripRepositoryPort;

  public TripService(TripRepositoryPort tripRepositoryPort) {
    this.tripRepositoryPort = tripRepositoryPort;
  }

  public Trip createTrip(Trip trip) {
    return tripRepositoryPort.save(trip);
  }

  public Trip getTripById(Long id) {
    return tripRepositoryPort.findById(id).orElse(null);
  }

  public void deleteTripById(Long id) {
    tripRepositoryPort.deleteById(id);
  }

  public Trip updateTrip(Trip trip) {
    return tripRepositoryPort.save(trip);
  }

  public List<Trip> getAllTrips() {
    return tripRepositoryPort.findAll();
  }

  public List<Trip> getTripsByOriginCityAndDestinationCity(String originCityName, String destinationCityName,
      String tripDate) {
    return tripRepositoryPort.findByOriginCityAndDestinationCity(originCityName, destinationCityName, tripDate);
  }

  public List<Trip> getTripsByOriginCityAndFinalDestinationCityWithStopover(String originCityName,
      String finalDestinationCityName, String tripDate) {
    return tripRepositoryPort.findByOriginCityAndFinalDestinationCityWithStopover(originCityName,
        finalDestinationCityName, tripDate);
  }

}
