package com.vuelosjanbi.plane.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.plane.application.ports.out.PlaneRepositoryPort;
import com.vuelosjanbi.plane.domain.models.Plane;

public class MySQLPlaneRepository implements PlaneRepositoryPort {
    private final String url;
    private final String username;
    private final String password;

    public MySQLPlaneRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Plane save(Plane plane) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO plane(plate, capacity, fabricationDate, status_id, model_id) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plane.getPlate());
                statement.setInt(2, plane.getCapacity());
                statement.setDate(3, plane.getFabricationDate());
                statement.setLong(4, plane.getStatus().getId());
                statement.setLong(4, plane.getModel().getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }

    public Plane update(Plane plane) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE plane SET description = ?, details = ?, value = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // statement.setString(1, plane.getDescription());
                // statement.setString(2, plane.getDetails());
                // statement.setDouble(3, plane.getValue());
                // statement.setLong(4, plane.getId());
                // statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }

    @Override
    public void deleteById(Long planeId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM plane WHERE id= ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, planeId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Plane> findById(Long planeId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane WHERE id = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, planeId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Plane plane = new Plane(
                        // resultSet.getString("description"),
                        // resultSet.getString("details"),
                        // resultSet.getDouble("value"));
                        // return Optional.of(plane);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Plane> findAll() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Plane> planes = new ArrayList<>();
                    while (resultSet.next()) {
                        // Plane plane = new Plane(
                        // resultSet.getLong("id"),
                        // resultSet.getString("description"),
                        // resultSet.getString("details"),
                        // resultSet.getDouble("value"));
                        // planes.add(plane);
                    }
                    return planes;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
