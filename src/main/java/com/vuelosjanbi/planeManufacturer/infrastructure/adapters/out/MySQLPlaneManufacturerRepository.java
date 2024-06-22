package com.vuelosjanbi.planeManufacturer.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.planeManufacturer.application.ports.out.PlaneManufacturerRepositoryPort;
import com.vuelosjanbi.planeManufacturer.domain.models.PlaneManufacturer;

public class MySQLPlaneManufacturerRepository implements PlaneManufacturerRepositoryPort {

    private final String url;
    private final String username;
    private final String password;

    public MySQLPlaneManufacturerRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public PlaneManufacturer save(PlaneManufacturer planeManufacturer) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO plane_manufacturer(name) VALUES(?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, planeManufacturer.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planeManufacturer;
    }

    public PlaneManufacturer update(PlaneManufacturer planeManufacturer) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE plane_manufacturer SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, planeManufacturer.getName());
                statement.setLong(2, planeManufacturer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planeManufacturer;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM plane_manufacturer WHERE id= ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PlaneManufacturer> findById(Long id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane_manufacturer WHERE id = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        PlaneManufacturer planeManufacturer = new PlaneManufacturer();
                        planeManufacturer.setId(resultSet.getLong("id"));
                        planeManufacturer.setName(resultSet.getString("name"));
                        return Optional.of(planeManufacturer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<PlaneManufacturer> findByName(String name) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane_manufacturer WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        PlaneManufacturer planeManufacturer = new PlaneManufacturer();
                        planeManufacturer.setId(resultSet.getLong("id"));
                        planeManufacturer.setName(resultSet.getString("name"));
                        return Optional.of(planeManufacturer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<PlaneManufacturer> findAll() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane_manufacturer";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<PlaneManufacturer> planeManufacturers = new ArrayList<>();
                    while (resultSet.next()) {
                        PlaneManufacturer planeManufacturer = new PlaneManufacturer();
                        planeManufacturer.setId(resultSet.getLong("id"));
                        planeManufacturer.setName(resultSet.getString("name"));
                        planeManufacturers.add(planeManufacturer);
                    }
                    return planeManufacturers;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
