package com.vuelosjanbi.trip.application.ports.out;

import com.vuelosjanbi.trip.domain.models.Trip;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TripRepositoryPort {

        Trip save(Trip trip);

        List<Trip> findAll();

        Optional<Trip> findById(Long id);

        void deleteById(Long id);

        @Query("SELECT DISTINCT t FROM Trip t " +
                        "JOIN t.flightConnections fc " +
                        "JOIN fc.originAirport oa " +
                        "JOIN oa.city oc " +
                        "JOIN fc.destinationAirport da " +
                        "JOIN da.city dc " +
                        "WHERE oc.name = :originCityName AND dc.name = :finalDestinationCityName AND t.tripDate = :tripDate")
        List<Trip> findByOriginCityAndDestinationCity(@Param("originCityName") String originCityName,
                        @Param("finalDestinationCityName") String finalDestinationCityName,
                        @Param("tripDate") Date tripDate);

        @Query("SELECT DISTINCT t FROM Trip t " +
                        "JOIN t.flightConnections fc1 " +
                        "JOIN fc1.originAirport oa1 " +
                        "JOIN oa1.city oc1 " +
                        "JOIN fc1.destinationAirport da1 " +
                        "JOIN da1.city dc1 " +
                        "JOIN t.flightConnections fc2 " +
                        "JOIN fc2.originAirport oa2 " +
                        "JOIN oa2.city oc2 " +
                        "JOIN fc2.destinationAirport da2 " +
                        "JOIN da2.city dc2 " +
                        "WHERE t = fc1.trip AND t = fc2.trip " +
                        "AND LOWER(oc1.name) = LOWER(:originCityName) " +
                        "AND LOWER(dc2.name) = LOWER(:finalDestinationCityName) " +
                        "AND LOWER(dc1.name) = LOWER(oc2.name) " +
                        "AND fc1.connectionNumber <> fc2.connectionNumber " +
                        "AND t.tripDate = :tripDate")
        List<Trip> findByOriginCityAndFinalDestinationCityWithStopover(
                        @Param("originCityName") String originCityName,
                        @Param("finalDestinationCityName") String finalDestinationCityName,
                        @Param("tripDate") Date tripDate);

}
