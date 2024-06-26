package com.vuelosjanbi.flightConnection.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionRepositoryPort;
import com.vuelosjanbi.flightConnection.domain.models.FlightConnection;
import com.vuelosjanbi.trip.domain.models.Trip;

public class MySQLFlightConnectionRepository implements FlightConnectionRepositoryPort {

  private final String url;
  private final String username;
  private final String password;

  public MySQLFlightConnectionRepository(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  @Override
  public FlightConnection save(FlightConnection flightConnection) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "INSERT INTO flightConnection (connection_numer,trip_id,plane_id,origin_airpot_id,destination_airport_id) VALUES (?, ?, ?, ?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, flightConnection.getConnectionNumber());
        statement.executeUpdate();
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            flightConnection.setId(generatedKeys.getLong(1));
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flightConnection;
  }

  public FlightConnection update(FlightConnection flightConnection) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "UPDATE flightConnection SET connectionNumber = ?,SET trip_id = ?,SET plane_id = ?, SET origin_airport_id=?, destination_airport_id = ? WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, flightConnection.getConnectionNumber());
        statement.setLong(2, flightConnection.getTrip().getId());
        statement.setLong(3, flightConnection.getPlane().getId());
        statement.setLong(4, flightConnection.getOriginAirport().getId());
        statement.setLong(5, flightConnection.getDestinationAirport().getId());
        statement.setLong(6, flightConnection.getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flightConnection;
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "DELETE FROM flightConnection WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<FlightConnection> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM flightConnection WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeQuery();
        if (statement.getResultSet().next()) {
          return Optional.of(new FlightConnection(statement.getResultSet().getLong("id"),
              statement.getResultSet().getString("connectionNumber")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public List<FlightConnection> findByPlaneId(Long planeId) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM flightConnection WHERE plane_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, planeId);
        try (ResultSet resultSet = statement.executeQuery()) {
          List<FlightConnection> flightConnections = new ArrayList<>();
          while (resultSet.next()) {
            flightConnections.add(new FlightConnection(resultSet.getLong("id"),
                resultSet.getString("connectionNumber")));
          }
          return flightConnections;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public List<FlightConnection> findAll() {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM flightConnection";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          List<FlightConnection> flightConnections = new ArrayList<>();
          while (resultSet.next()) {
            flightConnections.add(new FlightConnection(resultSet.getLong("id"),
                resultSet.getString("connectionNumber")));
          }
          return flightConnections;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public List<FlightConnection> findByPlanePlate(String plate) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM flightConnection f INNER JOIN plane p ON f.plane_id = p.id WHERE p.plate = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, plate);
        try (ResultSet resultSet = statement.executeQuery()) {
          List<FlightConnection> flightConnections = new ArrayList<>();
          while (resultSet.next()) {
            flightConnections
                .add(new FlightConnection(resultSet.getLong("id"), resultSet.getString("connection_number")));
          }
          return flightConnections;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public Optional<FlightConnection> findByTripId(Long tripId) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM flightConnection WHERE trip_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, tripId);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return Optional.of(new FlightConnection(resultSet.getLong("id"), resultSet.getString("connection_number")));
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public List<FlightConnection> findByTrip(Trip trip) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByTrip'");
  }
}