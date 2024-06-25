package com.vuelosjanbi.crewRole.infrastructure.adapters.out;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.vuelosjanbi.crewRole.application.ports.out.CrewRoleRepositoryport;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;

public class MySQLCrewRoleRepository implements CrewRoleRepositoryport {

  private final String url;
  private final String user;
  private final String password;

  public MySQLCrewRoleRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public CrewRole save(CrewRole crewRole) {
    String query = "INSERT INTO crew_role (name) VALUES(?)";
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      connection.setAutoCommit(false);

      try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, crewRole.getName());
        statement.executeUpdate();

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            crewRole.setId(generatedKeys.getLong(1));
          }
        }
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        throw new RuntimeException("Error saving CrewRole", e);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }

    return crewRole;
  }

  public CrewRole update(CrewRole crewRole) {
    String query = "UPDATE crew_role SET name = ? WHERE id = ?";
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      connection.setAutoCommit(false);
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, crewRole.getName());
        statement.setLong(2, crewRole.getId());
        statement.executeUpdate();

        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        throw new RuntimeException("Error updating CrewRole", e);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }

    return crewRole;
  }

  @Override
  public Optional<CrewRole> findById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM crew_role WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            CrewRole crewRole = new CrewRole();
            crewRole.setId(resultSet.getLong("id"));
            crewRole.setName(resultSet.getString("name"));
            return Optional.of(crewRole);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }
    return Optional.empty();
  }

  @Override
  public void deleteById(Long id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM crew_role WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, id);
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }
  }

  @Override
  public List<CrewRole> findAll() {
    List<CrewRole> crewRoles = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM crew_role";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            CrewRole crewRole = new CrewRole();
            crewRole.setId(resultSet.getLong("id"));
            crewRole.setName(resultSet.getString("name"));
            crewRoles.add(crewRole);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }
    return crewRoles;
  }

  @Override
  public Optional<CrewRole> findByName(String name) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM crew_role WHERE name = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, name);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            CrewRole crewRole = new CrewRole();
            crewRole.setId(resultSet.getLong("id"));
            crewRole.setName(resultSet.getString("name"));
            return Optional.of(crewRole);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }
    return Optional.empty();
  }

}
