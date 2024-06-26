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
import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.flightConnection.application.ports.out.FlightConnectionRepositoryPort;
import com.vuelosjanbi.trip.domain.models.Trip;
import com.vuelosjanbi.tripCrew.application.ports.out.TripCrewRepositoryPort;
import com.vuelosjanbi.tripCrew.domain.models.TripCrew;
import com.vuelosjanbi.tripCrew.domain.models.TripCrewId;

public class MySQLTripCrewRepository implements TripCrewRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  @Autowired
  private EmployeeRepositoryPort employeeRepositoryPort;

  @Autowired
  private FlightConnectionRepositoryPort flightConnectionRepositoryPort;

  public MySQLTripCrewRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  public MySQLTripCrewRepository(String url, String user, String password,
      EmployeeRepositoryPort employeeRepositoryPort,
      FlightConnectionRepositoryPort flightConnectionRepositoryPort) {
    this.url = url;
    this.user = user;
    this.password = password;
    this.employeeRepositoryPort = employeeRepositoryPort;
    this.flightConnectionRepositoryPort = flightConnectionRepositoryPort;
  }

  @Override
  public TripCrew save(TripCrew tripCrew) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO trip_crew (employee_id,flight_connection_id) VALUES (?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, tripCrew.getEmployee().getId());
        statement.setLong(2, tripCrew.getFlightConnection().getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return tripCrew;
  }

  public TripCrew update(TripCrew tripCrew) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "UPDATE trip_crew SET employee_id = ?, flight_connection_id = ? WHERE employee_id = ? AND flight_connection_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, tripCrew.getEmployee().getId());
        statement.setLong(2, tripCrew.getFlightConnection().getId());
        statement.setString(3, tripCrew.getId().getEmployeeId());
        statement.setLong(4, tripCrew.getId().getFlightConnectionId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return tripCrew;
  }

  @Override
  public Optional<TripCrew> findById(TripCrew id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM trip_crew WHERE employee_id = ? AND flight_connection_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, id.getEmployee().getId());
        statement.setLong(2, id.getFlightConnection().getId());
        statement.execute();
        try (ResultSet resultSet = statement.getResultSet()) {
          if (resultSet.next()) {
            TripCrew tripCrew = new TripCrew();
            TripCrewId tripCrewId = new TripCrewId();
            tripCrewId.setEmployeeId(resultSet.getString("employee_id"));
            tripCrewId.setFlightConnectionId(resultSet.getLong("flight_connection_id"));
            tripCrew.setEmployee(employeeRepositoryPort.findById(tripCrewId.getEmployeeId()).orElse(null));
            tripCrew.setFlightConnection(
                flightConnectionRepositoryPort.findById(tripCrewId.getFlightConnectionId()).orElse(null));
            tripCrew.setId(tripCrewId);
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
        statement.setString(1, id.getId().getEmployeeId());
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
            tripCrewId.setEmployeeId(statement.getResultSet().getString("employee_id"));
            tripCrewId.setFlightConnectionId(statement.getResultSet().getLong("flight_connection_id"));
            tripCrew.setId(tripCrewId);
            tripCrew.setEmployee(employeeRepositoryPort.findById(tripCrewId.getEmployeeId()).orElse(null));
            tripCrew.setFlightConnection(
                flightConnectionRepositoryPort.findById(tripCrewId.getFlightConnectionId()).orElse(null));
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

  @Override
  public List<TripCrew> findByEmployee(Employee id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByEmployeeId'");
  }

  @Override
  public List<TripCrew> findByFlightConnectionTripId(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByFlightConnectionTrip'");
  }

}
