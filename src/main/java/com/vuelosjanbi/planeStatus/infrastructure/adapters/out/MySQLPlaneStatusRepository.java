package com.vuelosjanbi.planeStatus.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeStatus.application.ports.out.PlaneStatusRepositoryPort;
import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;

public class MySQLPlaneStatusRepository implements PlaneStatusRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  public MySQLPlaneStatusRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public PlaneStatus save(PlaneStatus planeStatus) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO plane_status (name) VALUES (?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, planeStatus.getName());
        statement.executeUpdate();
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
          if (resultSet.next()) {
            planeStatus.setId(resultSet.getLong(1));
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return planeStatus;
  }

  public PlaneStatus update(PlaneStatus planeStatus) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "UPDATE plane_status SET name = ? WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, planeStatus.getName());
        statement.setLong(2, planeStatus.getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return planeStatus;
  }

  @Override
  public Optional<PlaneStatus> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM plane_status WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            PlaneStatus planeStatus = new PlaneStatus();
            planeStatus.setId(resultSet.getLong("id"));
            planeStatus.setName(resultSet.getString("name"));
            return Optional.of(planeStatus);
          }
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM plane_status WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<PlaneStatus> findAll() {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM plane_status";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          List<PlaneStatus> planeStatuses = new ArrayList<>();
          while (resultSet.next()) {
            PlaneStatus planeStatus = new PlaneStatus();
            planeStatus.setId(resultSet.getLong("id"));
            planeStatus.setName(resultSet.getString("name"));
            planeStatuses.add(planeStatus);
          }
          return planeStatuses;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

}
