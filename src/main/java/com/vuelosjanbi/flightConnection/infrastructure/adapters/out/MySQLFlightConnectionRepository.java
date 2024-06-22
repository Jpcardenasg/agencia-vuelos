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
      String query = "INSERT INTO flightConnection VALUES(?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, flightConnection.getConnectionNumber());
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
}