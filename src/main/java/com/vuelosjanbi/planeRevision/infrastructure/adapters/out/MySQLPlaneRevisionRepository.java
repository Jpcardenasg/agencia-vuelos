package com.vuelosjanbi.planeRevision.infrastructure.adapters.out;

import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vuelosjanbi.planeRevision.application.ports.out.PlaneRevisionRepositoryPort;
import com.vuelosjanbi.planeRevision.domain.models.PlaneRevision;

import java.util.ArrayList;

public class MySQLPlaneRevisionRepository implements PlaneRevisionRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  public MySQLPlaneRevisionRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public PlaneRevision save(PlaneRevision planeRevision) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO plane_revision (revision_date,plane_id) VALUES (?,?)";
      try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        statement.setDate(1, planeRevision.getRevisionDate());
        statement.executeUpdate();
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
          if (resultSet.next()) {
            planeRevision.setId(resultSet.getLong(1));
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return planeRevision;
  }

  public PlaneRevision update(PlaneRevision planeRevision) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "UPDATE plane_revision SET revision_date = ? WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setDate(1, planeRevision.getRevisionDate());
        statement.setLong(2, planeRevision.getId());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return planeRevision;
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM plane_revision WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<PlaneRevision> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM plane_revision WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            PlaneRevision planeRevision = new PlaneRevision();
            planeRevision.setId(resultSet.getLong("id"));
            planeRevision.setRevisionDate(resultSet.getDate("revision_date"));
            return Optional.of(planeRevision);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<PlaneRevision> findByPlaneId(Long planeId) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM plane_revision WHERE plane_id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, planeId);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            PlaneRevision planeRevision = new PlaneRevision();
            planeRevision.setId(resultSet.getLong("id"));
            planeRevision.setRevisionDate(resultSet.getDate("revision_date"));
            return Optional.of(planeRevision);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public List<PlaneRevision> findAll() {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM plane_revision";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          List<PlaneRevision> planeRevisions = new ArrayList<>();
          while (resultSet.next()) {
            PlaneRevision planeRevision = new PlaneRevision();
            planeRevision.setId(resultSet.getLong("id"));
            planeRevision.setRevisionDate(resultSet.getDate("revision_date"));
            planeRevisions.add(planeRevision);
          }
          return planeRevisions;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

}
