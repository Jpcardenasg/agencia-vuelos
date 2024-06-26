package com.vuelosjanbi.tripBooking.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.trip.application.ports.out.TripRepositoryPort;
import com.vuelosjanbi.trip.infrastructure.adapters.out.MySQLTripRepository;
import com.vuelosjanbi.tripBooking.application.ports.out.TripBookingRepositoryPort;
import com.vuelosjanbi.tripBooking.domain.models.TripBooking;

public class MySQLTripBookingRepository implements TripBookingRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  @Autowired
  private TripRepositoryPort tripRepositoryPort;

  public MySQLTripBookingRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
    this.tripRepositoryPort = new MySQLTripRepository(url, user, password);
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

  public TripBooking update(TripBooking tripBooking) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "UPDATE trip_booking SET date = ?, trip_id = ? WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setDate(1, tripBooking.getDate());
        statement.setLong(2, tripBooking.getTrip().getId());
        statement.setLong(3, tripBooking.getId());
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

  @Override
  public List<TripBooking> findByTripId(Long tripId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking WHERE trip_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, tripId);
        try (ResultSet resultSet = statement.executeQuery()) {
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
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public List<TripBooking> findByDate(Date date) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking WHERE date = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setDate(1, date);
        try (ResultSet resultSet = statement.executeQuery()) {
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
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public List<TripBooking> findBytripBookingDetailsCustomer(Customer customer) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_booking WHERE trip_id IN (SELECT id FROM trip WHERE customer_id = ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, customer.getId());
        try (ResultSet resultSet = statement.executeQuery()) {
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
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

}
