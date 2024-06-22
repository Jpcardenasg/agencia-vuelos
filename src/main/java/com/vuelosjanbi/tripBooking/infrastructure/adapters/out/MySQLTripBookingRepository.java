package com.vuelosjanbi.tripBooking.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.trip.application.ports.out.TripRepositoryPort;
import com.vuelosjanbi.tripBooking.application.ports.out.TripBookingRepositoryPort;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

public class MySQLTripBookingRepository implements TripBookingRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  @Autowired
  TripRepositoryPort tripRepositoryPort;

  public MySQLTripBookingRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public TripBooking save(TripBooking tripBooking) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO trip_booking (date, trip_id) VALUES (?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setDate(1, tripBooking.getDate());
        statement.setLong(2, tripBooking.getTrip().getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tripBooking;
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM trip_booking WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<TripBooking> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            TripBooking tripBooking = new TripBooking();
            tripBooking.setId(resultSet.getLong("id"));
            tripBooking.setDate(resultSet.getDate("date"));
            tripBooking.setTrip(tripRepositoryPort.findById(resultSet.getLong("trip_id")).orElse(null));
            return Optional.of(tripBooking);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public List<TripBooking> findAll() {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking";
      try (PreparedStatement statement = connection.prepareStatement(query);
          ResultSet resultSet = statement.executeQuery()) {
        List<TripBooking> tripBookings = new ArrayList<>();
        while (resultSet.next()) {
          TripBooking tripBooking = new TripBooking();
          tripBooking.setId(resultSet.getLong("id"));
          tripBooking.setDate(resultSet.getDate("date"));
          tripBooking.setTrip(tripRepositoryPort.findById(resultSet.getLong("trip_id")).orElse(null));
          tripBookings.add(tripBooking);
        }
        return tripBookings;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

}
