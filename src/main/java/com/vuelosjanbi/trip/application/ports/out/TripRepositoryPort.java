package com.vuelosjanbi.trip.application.ports.out;

import com.vuelosjanbi.trip.domain.models.Trip;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TripRepositoryPort {

  Trip save(Trip trip);

  List<Trip> findAll();

  Optional<Trip> findById(Long id);

  void deleteById(Long id);

  @Query("SELECT t FROM Trip t JOIN t.flightConnections fc " +
      "JOIN fc.originAirport oa JOIN oa.city oc " +
      "JOIN fc.destinationAirport da JOIN da.city dc " +
      "WHERE oc.name = :originCityName AND dc.name = :destinationCityName AND t.tripDate = :tripDate")
  List<Trip> findByOriginCityAndDestinationCity(
      @Param("originCityName") String originCityName,
      @Param("destinationCityName") String destinationCityName, @Param("tripDate") String tripDate);

  @Query("SELECT t FROM Trip t JOIN t.flightConnections fc1 " +
      "JOIN fc1.originAirport oa1 JOIN oa1.city oc1 " +
      "JOIN fc1.destinationAirport da1 JOIN da1.city dc1, " +
      "FlightConnection fc2 JOIN fc2.originAirport oa2 JOIN oa2.city oc2 " +
      "JOIN fc2.destinationAirport da2 JOIN da2.city dc2 " +
      "WHERE t = fc1.trip AND t = fc2.trip " +
      "AND oc1.name = :originCityName " +
      "AND dc2.name = :finalDestinationCityName " +
      "AND dc1.name = oc2.name " +
      "AND fc1.connectionNumber <> fc2.connectionNumber AND t.tripDate = :tripDate")
  List<Trip> findByOriginCityAndFinalDestinationCityWithStopover(
      @Param("originCityName") String originCityName,
      @Param("finalDestinationCityName") String finalDestinationCityName, @Param("tripDate") String tripDate);

}
