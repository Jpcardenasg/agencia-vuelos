package com.vuelosjanbi.trip.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.trip.application.ports.out.TripRepositoryPort;
import com.vuelosjanbi.trip.domain.models.Trip;

public class MySQLTripRepository implements TripRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  MySQLTripRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public Trip save(Trip trip) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO trip (trip_date, price_trip) VALUES (?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setDate(1, trip.getTripDate());
        statement.setDouble(2, trip.getTripPrice());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return trip;
  }

  @Override
  public List<Trip> findAll() {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          List<Trip> trips = new ArrayList<>();
          while (resultSet.next()) {
            Trip trip = new Trip();
            trip.setId(resultSet.getLong("id"));
            trip.setTripDate(resultSet.getDate("trip_date"));
            trip.setTripPrice(resultSet.getDouble("trip_price"));
            trips.add(trip);
          }
          return trips;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public Optional<Trip> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            Trip trip = new Trip();
            trip.setId(resultSet.getLong("id"));
            trip.setTripDate(resultSet.getDate("trip_date"));
            trip.setTripPrice(resultSet.getDouble("trip_price"));
            return Optional.of(trip);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM trip WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
