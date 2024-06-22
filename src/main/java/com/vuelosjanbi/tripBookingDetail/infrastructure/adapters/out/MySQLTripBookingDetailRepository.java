package com.vuelosjanbi.tripBookingDetail.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;

import com.vuelosjanbi.customer.application.ports.out.CustomerRepositoryPort;
import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.flightFare.application.ports.out.FlightFareRepositoryPort;
import com.vuelosjanbi.tripBooking.application.ports.out.TripBookingRepositoryPort;
import com.vuelosjanbi.tripBookingDetail.application.ports.out.TripBookingDetailRepositoryPort;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;

public class MySQLTripBookingDetailRepository implements TripBookingDetailRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  @Autowired
  TripBookingRepositoryPort tripBookingRepositoryPort;
  @Autowired
  CustomerRepositoryPort customerRepositoryPort;
  @Autowired
  FlightFareRepositoryPort flightFareRepositoryPort;

  MySQLTripBookingDetailRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public TripBookingDetail save(TripBookingDetail tripBookingDetail) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO trip_booking_detail (trip_booking_id, customer_id,flight_fare_id) VALUES (?, ?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, tripBookingDetail.getTripBooking().getId());
        statement.setString(2, tripBookingDetail.getCustomer().getId());
        statement.setLong(3, tripBookingDetail.getFlightFare().getId());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tripBookingDetail;
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM trip_booking_detail WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<TripBookingDetail> findByCustomer(Customer customer) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking_detail WHERE customer_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, customer.getId());
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            TripBookingDetail tripBookingDetail = new TripBookingDetail();
            tripBookingDetail.setId(resultSet.getLong("id"));
            tripBookingDetail.setTripBooking(
                tripBookingRepositoryPort.findById(resultSet.getLong("trip_booking_id")).orElse(null));
            tripBookingDetail.setCustomer(
                customerRepositoryPort.findById(resultSet.getString("customer_id")).orElse(null));
            tripBookingDetail.setFlightFare(flightFareRepositoryPort.findById(resultSet.getLong("flight_fare_id"))
                .orElse(null));
            return Optional.of(tripBookingDetail);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<TripBookingDetail> findByCustomerId(String id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking_detail WHERE customer_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            TripBookingDetail tripBookingDetail = new TripBookingDetail();
            tripBookingDetail.setId(resultSet.getLong("id"));
            tripBookingDetail.setTripBooking(
                tripBookingRepositoryPort.findById(resultSet.getLong("trip_booking_id")).orElse(null));
            tripBookingDetail.setCustomer(
                customerRepositoryPort.findById(resultSet.getString("customer_id")).orElse(null));
            tripBookingDetail.setFlightFare(flightFareRepositoryPort.findById(resultSet.getLong("flight_fare_id"))
                .orElse(null));
            return Optional.of(tripBookingDetail);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<TripBookingDetail> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking_detail WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            TripBookingDetail tripBookingDetail = new TripBookingDetail();
            tripBookingDetail.setId(resultSet.getLong("id"));
            tripBookingDetail.setTripBooking(
                tripBookingRepositoryPort.findById(resultSet.getLong("trip_booking_id")).orElse(null));
            tripBookingDetail.setCustomer(
                customerRepositoryPort.findById(resultSet.getString("customer_id")).orElse(null));
            tripBookingDetail.setFlightFare(flightFareRepositoryPort.findById(resultSet.getLong("flight_fare_id"))
                .orElse(null));
            return Optional.of(tripBookingDetail);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public List<TripBookingDetail> findAll() {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking_detail";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          List<TripBookingDetail> tripBookingDetails = new ArrayList<>();
          while (resultSet.next()) {
            TripBookingDetail tripBookingDetail = new TripBookingDetail();
            tripBookingDetail.setId(resultSet.getLong("id"));
            tripBookingDetail.setTripBooking(
                tripBookingRepositoryPort.findById(resultSet.getLong("trip_booking_id")).orElse(null));
            tripBookingDetail.setCustomer(
                customerRepositoryPort.findById(resultSet.getString("customer_id")).orElse(null));
            tripBookingDetail.setFlightFare(flightFareRepositoryPort.findById(resultSet.getLong("flight_fare_id"))
                .orElse(null));
            tripBookingDetails.add(tripBookingDetail);
          }
          return tripBookingDetails;
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

}
