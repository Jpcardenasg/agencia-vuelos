package com.vuelosjanbi.planeRevisionEmployee.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeRevisionEmployee.application.ports.out.PlaneRevisionEmployeeRepositoryPort;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlaneRevisionEmployee;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.RevisionEmployeeId;

public class MySQLPlaneRevisionEmployeeRepository implements PlaneRevisionEmployeeRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  public MySQLPlaneRevisionEmployeeRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public PlaneRevisionEmployee save(PlaneRevisionEmployee planeRevisionEmployee) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO plane_revision_employee (employee_id, plane_revision_id) VALUES (?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, planeRevisionEmployee.getEmployee().getId());
        statement.setLong(2, planeRevisionEmployee.getPlanRevision().getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return planeRevisionEmployee;
  }

  public PlaneRevisionEmployee update(PlaneRevisionEmployee planeRevisionEmployee) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "UPDATE plane_revision_employee SET employee_id = ?, plane_revision_id = ? WHERE employee_id = ? AND plane_revision_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, planeRevisionEmployee.getEmployee().getId());
        statement.setLong(2, planeRevisionEmployee.getPlanRevision().getId());
        statement.setString(3, planeRevisionEmployee.getEmployee().getId());
        statement.setLong(4, planeRevisionEmployee.getPlanRevision().getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return planeRevisionEmployee;
  }

  @Override
  public Optional<PlaneRevisionEmployee> findById(RevisionEmployeeId id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM plane_revision_employee WHERE employee_id = ? AND plane_revision_id";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            PlaneRevisionEmployee planeRevisionEmployee = new PlaneRevisionEmployee();
            RevisionEmployeeId revisionEmployeeId = new RevisionEmployeeId();
            revisionEmployeeId.setEmployeeId(resultSet.getString("employee_id"));
            revisionEmployeeId.setPlaneRevisionId(resultSet.getLong("plane_revision_id"));
            planeRevisionEmployee.setId(revisionEmployeeId);
            return Optional.of(planeRevisionEmployee);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void deleteById(RevisionEmployeeId id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM plane_revision_employee WHERE employee_id = ? AND plan_revision_id";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, id.getEmployeeId());
        statement.setLong(2, id.getPlaneRevisionId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public PlaneRevisionEmployee findByEmployeeId(String employeeId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM plane_revision_employee WHERE employee_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, employeeId);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            PlaneRevisionEmployee planeRevisionEmployee = new PlaneRevisionEmployee();
            RevisionEmployeeId revisionEmployeeId = new RevisionEmployeeId();
            revisionEmployeeId.setEmployeeId(resultSet.getString("employee_id"));
            revisionEmployeeId.setPlaneRevisionId(resultSet.getLong("plane_revision_id"));
            planeRevisionEmployee.setId(revisionEmployeeId);
            return planeRevisionEmployee;
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<PlaneRevisionEmployee> findAll() {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM plane_revision_employee";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          List<PlaneRevisionEmployee> planeRevisionEmployees = new ArrayList<>();
          while (resultSet.next()) {
            PlaneRevisionEmployee planeRevisionEmployee = new PlaneRevisionEmployee();
            RevisionEmployeeId revisionEmployeeId = new RevisionEmployeeId();
            revisionEmployeeId.setEmployeeId(resultSet.getString("employee_id"));
            revisionEmployeeId.setPlaneRevisionId(resultSet.getLong("plane_revision_id"));
            planeRevisionEmployee.setId(revisionEmployeeId);
            planeRevisionEmployees.add(planeRevisionEmployee);
          }
          return planeRevisionEmployees;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

}
