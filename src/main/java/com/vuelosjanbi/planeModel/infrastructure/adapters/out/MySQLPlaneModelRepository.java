package com.vuelosjanbi.planeModel.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.planeManufacturer.application.ports.out.PlaneManufacturerRepositoryPort;
import com.vuelosjanbi.planeModel.application.ports.out.PlaneModelRepositoryPort;
import com.vuelosjanbi.planeModel.domain.models.PlaneModel;

public class MySQLPlaneModelRepository implements PlaneModelRepositoryPort {

    private final String url;
    private final String username;
    private final String password;

    @Autowired
    PlaneManufacturerRepositoryPort planeManufacturerRepositoryPort;

    public MySQLPlaneModelRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public PlaneModel save(PlaneModel planeModel) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO plane_model(name) VALUES(?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, planeModel.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planeModel;
    }

    public PlaneModel update(PlaneModel planeModel) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE plane_model SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, planeModel.getName());
                statement.setLong(2, planeModel.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planeModel;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM plane_model WHERE id= ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PlaneModel> findById(Long id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane_model WHERE id = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        PlaneModel planemodel = new PlaneModel();
                        planemodel.setId(resultSet.getLong("id"));
                        planemodel.setName(resultSet.getString("name"));
                        planemodel.setPlaneManufacturer(
                                planeManufacturerRepositoryPort.findById(resultSet.getLong("plane_manufacturer_id"))
                                        .orElse(null));
                        return Optional.of(planemodel);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<PlaneModel> findByName(String name) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane_model WHERE name = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        PlaneModel planemodel = new PlaneModel();
                        planemodel.setId(resultSet.getLong("id"));
                        planemodel.setName(resultSet.getString("name"));
                        planemodel.setPlaneManufacturer(
                                planeManufacturerRepositoryPort.findById(resultSet.getLong("plane_manufacturer_id"))
                                        .orElse(null));
                        return Optional.of(planemodel);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<PlaneModel> findByPlaneManufacturerName(String manufacturerName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane_model mo INNER JOIN plane_manufacturer ma ON mo.plane_manufacturer_id = ma.id WHERE ma.name = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, manufacturerName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<PlaneModel> planeModels = new ArrayList<>();
                    while (resultSet.next()) {
                        PlaneModel planemodel = new PlaneModel();
                        planemodel.setId(resultSet.getLong("id"));
                        planemodel.setName(resultSet.getString("name"));
                        planemodel.setPlaneManufacturer(
                                planeManufacturerRepositoryPort.findById(resultSet.getLong("plane_manufacturer_id"))
                                        .orElse(null));
                        planeModels.add(planemodel);
                    }
                    return planeModels;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<PlaneModel> findAll() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM plane_model";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<PlaneModel> planeModels = new ArrayList<>();
                    while (resultSet.next()) {
                        PlaneModel planemodel = new PlaneModel();
                        planemodel.setId(resultSet.getLong("id"));
                        planemodel.setName(resultSet.getString("name"));
                        planemodel.setPlaneManufacturer(
                                planeManufacturerRepositoryPort.findById(resultSet.getLong("plane_manufacturer_id"))
                                        .orElse(null));
                        planeModels.add(planemodel);
                    }
                    return planeModels;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
