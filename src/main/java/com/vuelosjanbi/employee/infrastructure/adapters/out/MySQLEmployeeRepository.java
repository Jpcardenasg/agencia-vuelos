package com.vuelosjanbi.employee.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.airline.application.ports.out.AirlineRepositoryPort;

import com.vuelosjanbi.airport.application.ports.out.AirportRepositoryPort;
import com.vuelosjanbi.crewRole.application.ports.out.CrewRoleRepositoryport;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;
import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.employee.domain.models.Employee;

public class MySQLEmployeeRepository implements EmployeeRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  @Autowired
  CrewRoleRepositoryport crewRoleRepositoryport;
  AirlineRepositoryPort airlineRepositoryPort;
  AirportRepositoryPort airportRepositoryPort;

  public MySQLEmployeeRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public Employee save(Employee employee) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO employee (name, rol_id, airline_id, airport_id) VALUES (?, ?, ?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, employee.getName());
        statement.setLong(2, employee.getRol().getId());
        statement.setLong(3, employee.getAirline().getId());
        statement.setLong(4, employee.getAirport().getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employee;
  }

  @Override
  public Optional<Employee> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM employee WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet result = statement.executeQuery()) {
          if (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getLong("id"));
            employee.setName(result.getString("name"));
            employee.setRol(crewRoleRepositoryport.findById(result.getLong("rol_id")).orElse(null));
            employee.setAirline(airlineRepositoryPort.findById(result.getLong("airline_id")).orElse(null));
            employee.setAirport(airportRepositoryPort.findById(result.getLong("airport_id")).orElse(null));
            return Optional.of(employee);
          }
          return Optional.empty();
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM employee WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Employee> findAll() {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM employee";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet result = statement.executeQuery()) {
          List<Employee> employees = new ArrayList<>();
          while (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getLong("id"));
            employee.setName(result.getString("name"));
            employee.setRol(crewRoleRepositoryport.findById(result.getLong("rol_id")).orElse(null));
            employee.setAirline(airlineRepositoryPort.findById(result.getLong("airline_id")).orElse(null));
            employee.setAirport(airportRepositoryPort.findById(result.getLong("airport_id")).orElse(null));
            employees.add(employee);
          }
          return employees;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public List<Employee> findByRolId(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM employee WHERE rol_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet result = statement.executeQuery()) {
          List<Employee> employees = new ArrayList<>();
          while (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getLong("id"));
            employee.setName(result.getString("name"));
            employee.setRol(crewRoleRepositoryport.findById(result.getLong("rol_id")).orElse(null));
            employee.setAirline(airlineRepositoryPort.findById(result.getLong("airline_id")).orElse(null));
            employee.setAirport(airportRepositoryPort.findById(result.getLong("airport_id")).orElse(null));
            employees.add(employee);
          }
          return employees;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public List<Employee> findByRol(CrewRole Rol) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM employee WHERE rol_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, Rol.getId());
        try (ResultSet result = statement.executeQuery()) {
          List<Employee> employees = new ArrayList<>();
          while (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getLong("id"));
            employee.setName(result.getString("name"));
            employee.setRol(crewRoleRepositoryport.findById(result.getLong("rol_id")).orElse(null));
            employee.setAirline(airlineRepositoryPort.findById(result.getLong("airline_id")).orElse(null));
            employee.setAirport(airportRepositoryPort.findById(result.getLong("airport_id")).orElse(null));
            employees.add(employee);
          }
          return employees;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public List<Employee> findEmployeesByAirlineId(String airlineId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM employee WHERE airline_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, airlineId);
        try (ResultSet result = statement.executeQuery()) {
          List<Employee> employees = new ArrayList<>();
          while (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getLong("id"));
            employee.setName(result.getString("name"));
            employee.setRol(crewRoleRepositoryport.findById(result.getLong("rol_id")).orElse(null));
            employee.setAirline(airlineRepositoryPort.findById(result.getLong("airline_id")).orElse(null));
            employee.setAirport(airportRepositoryPort.findById(result.getLong("airport_id")).orElse(null));
            employees.add(employee);
          }
          return employees;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}