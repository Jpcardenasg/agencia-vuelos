package com.vuelosjanbi.tripCrew.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionsRepositoryPort;
import com.vuelosjanbi.tripCrew.application.ports.out.TripCrewRepositoryPort;
import com.vuelosjanbi.tripCrew.domain.models.TripCrew;

public class MySQLTripCrewRepository implements TripCrewRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  public MySQLTripCrewRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Autowired
  EmployeeRepositoryPort employeeRepositoryPort;

  @Autowired
  FlightConnectionsRepositoryPort flightConnectionRepositoryPort;

  @Override
  public TripCrew save(TripCrew tripCrew) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO trip_crew (employee_id,flight_connection_id) VALUES (?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, tripCrew.getEmployee().getId());
        statement.setLong(2, tripCrew.getFlightConnection().getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return tripCrew;
  }

  @Override
  public Optional<TripCrew> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_crew WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.execute();
        if (statement.getResultSet().next()) {
          TripCrew tripCrew = new TripCrew();
          tripCrew.setId(statement.getResultSet().getLong("id"));
          return Optional.of(tripCrew);
        } else {
          return Optional.empty();
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM trip_crew WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<TripCrew> findAll() {
    List<TripCrew> tripCrews = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_crew";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            TripCrew tripCrew = new TripCrew();
            tripCrew.setId(resultSet.getLong("id"));
            tripCrew.setEmployee(employeeRepositoryPort.findById(resultSet.getLong("employee_id")).orElse(null));
            tripCrew.setFlightConnection(
                flightConnectionRepositoryPort.findById(resultSet.getLong("flight_connection_id")).orElse(null));
            tripCrews.add(tripCrew);
          }
          return tripCrews;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<TripCrew>();
    }
  }

}
