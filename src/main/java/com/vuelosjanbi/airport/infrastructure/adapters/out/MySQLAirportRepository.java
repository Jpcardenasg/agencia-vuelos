package com.vuelosjanbi.airport.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import java.util.ArrayList;

import com.vuelosjanbi.airport.application.ports.out.AirportRepositoryPort;
import com.vuelosjanbi.airport.domain.models.Airport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLAirportRepository implements AirportRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  public MySQLAirportRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public Airport save(Airport airport) {
    try (Connection connection = DriverManager.getConnection(user, url, password)) {
      String query;
      if (airport.getCity() == null) {
        query = "INSERT INTO airport (name) VALUES (?)";
      } else {
        query = "INSERT INTO airport (name, city_id) VALUES (?, ?)";
      }
      try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, airport.getName());
        if (airport.getCity() != null) {
          statement.setLong(2, airport.getCity().getId());
        }
        statement.executeUpdate();
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            airport.setId(generatedKeys.getLong(1));
          }
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airport;
  }

  public Airport update(Airport airport) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query;
      if (airport.getCity() == null) {
        query = "UPDATE airport SET name = ? WHERE id = ?";
      } else {
        query = "UPDATE airport SET name = ?, city_id = ? WHERE id = ?";
      }
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, airport.getName());
        if (airport.getCity() != null) {
          statement.setLong(2, airport.getCity().getId());
          statement.setLong(3, airport.getId());
        } else {
          statement.setLong(2, airport.getId());
        }

        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airport;
  }

  @Override
  public void deleteById(Long airportId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM airport WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, airportId);
        statement.executeUpdate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Airport> findAll() {
    List<Airport> airports = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM airport";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        ResultSet resulSet = statement.executeQuery();
        while (resulSet.next()) {
          Airport airport = new Airport();
          airport.setId(resulSet.getLong("id"));
          airport.setName(resulSet.getString("name"));
          airports.add(airport);
        }

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return airports;
  }

  @Override
  public Optional<Airport> findById(Long airportId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM airport WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, airportId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
          Airport airport = new Airport();
          airport.setId(resultSet.getLong("id"));
          airport.setName(resultSet.getString("name"));
          return Optional.of(airport);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<Airport> findByName(String airportName) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM airport WHERE name = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, airportName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
          Airport airport = new Airport();
          airport.setId(resultSet.getLong("id"));
          airport.setName(resultSet.getString("name"));
          return Optional.of(airport);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public List<Airport> findAirportsByCityId(Long cityId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM airport WHERE city_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, cityId);
        ResultSet resultSet = statement.executeQuery();
        List<Airport> airports = new ArrayList<>();
        while (resultSet.next()) {
          Airport airport = new Airport();
          airport.setId(resultSet.getLong("id"));
          airport.setName(resultSet.getString("name"));
          airports.add(airport);
        }
        return airports;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}
