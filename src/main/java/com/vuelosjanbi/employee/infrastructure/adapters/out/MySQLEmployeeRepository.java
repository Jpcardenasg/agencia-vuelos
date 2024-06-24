package com.vuelosjanbi.employee.infrastructure.adapters.out;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.employee.application.ports.out.EmployeeRepositoryPort;
import com.vuelosjanbi.employee.domain.models.Employee;

public class MySQLEmployeeRepository implements EmployeeRepositoryPort {

  private final String url;
  private final String username;
  private final String password;

  public MySQLEmployeeRepository(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  @Override
  public Employee save(Employee employee) {
    String tableName = "employee";
    StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
    queryBuilder.append(tableName).append(" (name");

    StringBuilder placeholders = new StringBuilder("VALUES (?)");

    List<Object> params = new ArrayList<>();
    params.add(employee.getName());

    Field[] fields = employee.getClass().getDeclaredFields();

    for (Field field : fields) {
      field.setAccessible(true);

      if ("id".equals(field.getName())) {
        continue;
      }
      try {
        Object value = field.get(employee);
        if (value != null) {
          queryBuilder.append(", ").append(field.getName());
          placeholders.append(", ?");
          params.add(value);
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    queryBuilder.append(") ").append(placeholders);

    String query = queryBuilder.toString();

    try (Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

      // Establecer los parámetros en la consulta preparada
      for (int i = 0; i < params.size(); i++) {
        statement.setObject(i + 1, params.get(i));
      }

      statement.executeUpdate();
      // TODO: Id no es generado ahora es String porque es el número de identifación
      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          employee.setId(generatedKeys.getString(1));
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employee;
  }

  public Employee update(Employee employee, String oldId) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "UPDATE employee SET id = ?, name = ?, entry_date = ?, rol_id = ?, airline_id = ?, airport_id = ? WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, employee.getId());
        statement.setString(2, employee.getName());
        statement.setDate(3, employee.getEntryDate());
        statement.setLong(4, employee.getRol().getId());
        statement.setLong(5, employee.getAirline().getId());
        statement.setLong(6, employee.getAirport().getId());
        statement.setString(7, oldId);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employee;
  }

  @Override
  public Optional<Employee> findById(String id) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM employee WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, id);
        try (ResultSet result = statement.executeQuery()) {
          if (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getString("id"));
            employee.setName(result.getString("name"));
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
  public void deleteById(String id) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "DELETE FROM employee WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Employee> findAll() {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM employee";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet result = statement.executeQuery()) {
          List<Employee> employees = new ArrayList<>();
          while (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getString("id"));
            employee.setName(result.getString("name"));

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
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM employee WHERE rol_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet result = statement.executeQuery()) {
          List<Employee> employees = new ArrayList<>();
          while (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getString("id"));
            employee.setName(result.getString("name"));
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
  public List<Employee> findEmployeesByAirlineId(Long airlineId) {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      String query = "SELECT * FROM employee WHERE airline_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, airlineId);
        try (ResultSet result = statement.executeQuery()) {
          List<Employee> employees = new ArrayList<>();
          while (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getString("id"));
            employee.setName(result.getString("name"));

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