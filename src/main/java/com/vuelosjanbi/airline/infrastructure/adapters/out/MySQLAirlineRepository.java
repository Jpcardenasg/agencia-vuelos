package com.vuelosjanbi.airline.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import java.util.ArrayList;
import java.sql.Statement;

import java.sql.Connection;
import com.vuelosjanbi.airline.application.ports.out.AirlineRepositoryPort;
import com.vuelosjanbi.airline.domain.models.Airline;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLAirlineRepository implements AirlineRepositoryPort {

  private final String url;
  private final String username;
  private final String password;

  public MySQLAirlineRepository(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  @Override
  public Airline save(Airline airline) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "INSERT INTO airline (name) VALUES (?)";
      try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, airline.getName());
        statement.executeUpdate();
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            airline.setId(generatedKeys.getLong(1));
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airline;
  }

  public Airline update(Airline airline, String name) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "UPDATE airline SET name = ? WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, airline.getName());
        statement.setLong(2, airline.getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airline;
  }

  @Override
  public void deleteById(Long airlineId) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "DELETE FROM airline WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, airlineId);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Airline> findAll() {
    List<Airline> airlines = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM airline";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.execute();
        try (ResultSet resultSet = statement.getResultSet()) {
          while (resultSet.next()) {
            Airline airline = new Airline();
            airline.setId(resultSet.getLong("id"));
            airline.setName(resultSet.getString("name"));
            airlines.add(airline);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airlines;
  }

  @Override
  public Optional<Airline> findById(Long airlineId) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM airline WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, airlineId);
        statement.execute();
        try (ResultSet resultSet = statement.getResultSet()) {
          if (resultSet.next()) {
            Airline airline = new Airline();
            airline.setId(resultSet.getLong("id"));
            airline.setName(resultSet.getString("name"));
            return Optional.of(airline);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<Airline> findByName(String airlineName) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM airline WHERE name = ?";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, airlineName);
        preparedStatement.execute();
        try (ResultSet resultSet = preparedStatement.getResultSet()) {
          if (resultSet.next()) {
            Airline airline = new Airline();
            airline.setId(resultSet.getLong("id"));
            airline.setName(resultSet.getString("name"));
            return Optional.of(airline);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

}
