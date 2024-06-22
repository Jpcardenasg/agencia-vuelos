package com.vuelosjanbi.planeStatus.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.plane.application.ports.out.PlaneRepositoryPort;
import com.vuelosjanbi.planeStatus.application.ports.out.PlaneStatusRepositoryPort;
import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;

public class MySQLPlaneStatusRepository implements PlaneStatusRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  @Autowired
  PlaneRepositoryPort planeRepositoryPort;

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
            planeStatus.setPlanes(planeRepositoryPort.findAll());
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
            planeStatus.setPlanes(planeRepositoryPort.findAll());
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
