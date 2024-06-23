package com.vuelosjanbi.airportGate.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vuelosjanbi.airportGate.application.port.out.AirportGateRepositoryPort;
import com.vuelosjanbi.airportGate.domain.models.AirportGate;

public class MySQLAirportGateRepository implements AirportGateRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  public MySQLAirportGateRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public AirportGate save(AirportGate airportGate) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query;
      if (airportGate.getAirport() == null) {
        query = "INSERT INTO airport_gate (gate) VALUES (?)";
      } else {
        query = "INSERT INTO airport_gate (gate, airport_id) VALUES (?, ?)";
      }
      try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, airportGate.getGate());
        if (airportGate.getAirport() != null) {
          statement.setLong(2, airportGate.getAirport().getId());
        }
        statement.executeUpdate();
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            airportGate.setId(generatedKeys.getLong(1));
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airportGate;
  }

  public AirportGate update(AirportGate airportGate) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query;
      if (airportGate.getAirport() == null) {
        query = "UPDATE airport_gate SET gate = ? WHERE id = ?";
      } else {
        query = "UPDATE airport_gate SET gate = ?, airport_id = ? WHERE id = ?";
      }
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, airportGate.getGate());
        if (airportGate.getAirport() != null) {
          statement.setLong(2, airportGate.getAirport().getId());
          statement.setLong(3, airportGate.getId());
        }
        statement.setLong(2, airportGate.getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airportGate;
  }

  @Override
  public void deleteById(Long airportGateId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM airport_gate WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, airportGateId);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<AirportGate> findAll() {
    List<AirportGate> airportGates = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM airport_gate";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            AirportGate airportGate = new AirportGate();
            airportGate.setId(resultSet.getLong("id"));
            airportGate.setGate(resultSet.getString("gate"));
            airportGates.add(airportGate);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airportGates;
  }

  @Override
  public Optional<AirportGate> findById(Long airportGateId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM airport_gate WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, airportGateId);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            AirportGate airportGate = new AirportGate();
            airportGate.setId(resultSet.getLong("id"));
            airportGate.setGate(resultSet.getString("gate"));
            return Optional.of(airportGate);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<AirportGate> findByGate(String gate) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM airport_gate WHERE gate = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, gate);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            AirportGate airportGate = new AirportGate();
            airportGate.setId(resultSet.getLong("id"));
            airportGate.setGate(resultSet.getString("gate"));
            return Optional.of(airportGate);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

}
