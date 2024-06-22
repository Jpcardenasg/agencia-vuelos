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
import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionRepositoryPort;
import com.vuelosjanbi.tripCrew.application.ports.out.TripCrewRepositoryPort;
import com.vuelosjanbi.tripCrew.domain.models.TripCrew;
import com.vuelosjanbi.tripCrew.domain.models.TripCrewId;

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
  FlightConnectionRepositoryPort flightConnectionRepositoryPort;

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
  public Optional<TripCrew> findById(TripCrewId id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_crew WHERE employee_id = ? AND flight_connection_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id.getEmployeeId());
        statement.setLong(2, id.getFlightConnectionId());
        statement.execute();
        try (ResultSet resultSet = statement.getResultSet()) {
          if (resultSet.next()) {
            TripCrew tripCrew = new TripCrew();
            TripCrewId tripCrewId = new TripCrewId();
            tripCrewId.setEmployeeId(resultSet.getLong("employee_id"));
            tripCrewId.setFlightConnectionId(resultSet.getLong("flight_connection_id"));
            tripCrew.setId(tripCrewId);
            tripCrew.setEmployee(employeeRepositoryPort.findById(resultSet.getLong("employee_id")).orElse(null));
            tripCrew.setFlightConnection(
                flightConnectionRepositoryPort.findById(resultSet.getLong("flight_connection_id")).orElse(null));
            return Optional.of(tripCrew);
          } else {
            return Optional.empty();
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void deleteById(TripCrew id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM trip_crew WHERE id = ? AND flight_connection_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id.getId().getEmployeeId());
        statement.setLong(2, id.getId().getFlightConnectionId());
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
            TripCrewId tripCrewId = new TripCrewId();
            tripCrewId.setEmployeeId(statement.getResultSet().getLong("employee_id"));
            tripCrewId.setFlightConnectionId(statement.getResultSet().getLong("flight_connection_id"));
            tripCrew.setId(tripCrewId);
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
