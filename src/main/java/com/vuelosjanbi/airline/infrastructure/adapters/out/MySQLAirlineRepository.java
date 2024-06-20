package com.vuelosjanbi.airline.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;

import java.sql.Connection;
import com.vuelosjanbi.airline.application.ports.out.AirlineRepositoryPort;
import com.vuelosjanbi.airline.domain.models.Airline;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLAirlineRepository implements AirlineRepositoryPort {

  private final String url;
  private final String username;
  private final String password;

  MySQLAirlineRepository(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  @Override
  public Airline save(Airline airline) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "INSERT INTO airline (name) VALUES (?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, airline.getName());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return airline;
  }

  @Override
  public void deleteById(Long airlineId) {
  }

  @Override
  public List<Airline> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public Optional<Airline> findById(Long airlineId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public Optional<Airline> findByName(String airlineName) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByName'");
  }

}
