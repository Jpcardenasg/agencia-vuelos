package com.vuelosjanbi.plane.infrastructure.adapters.out;

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
import com.vuelosjanbi.plane.domain.models.Plane;
import com.vuelosjanbi.planeModel.application.ports.out.PlaneModelRepositoryPort;
import com.vuelosjanbi.planeStatus.application.ports.out.PlaneStatusRepositoryPort;

public class MySQLPlaneRepository implements PlaneRepositoryPort {
    private final String url;
    private final String username;
    private final String password;

    @Autowired
    PlaneStatusRepositoryPort planeStatusRepositoryPort;

    @Autowired
    PlaneModelRepository planeModelRepositoryPort;

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
                statement.setLong(5, plane.getModel().getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }

    public Plane update(Plane plane) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE plane SET plate = ?, capacity = ?, fabricationDate = ?, status_id = ?, model_id = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plane.getPlate());
                statement.setInt(2, plane.getCapacity());
                statement.setDate(3, plane.getFabricationDate());
                statement.setLong(4, plane.getStatus().getId());
                statement.setLong(5, plane.getModel().getId());
                statement.setLong(6, plane.getId());
                statement.executeUpdate();
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
                        Plane plane = new Plane();
                        plane.setId(resultSet.getLong("id"));
                        plane.setPlate(resultSet.getString("plate"));
                        plane.setCapacity(resultSet.getInt("capacity"));
                        plane.setFabricationDate(resultSet.getDate("fabrication_date"));
                        plane.setStatus(
                                planeStatusRepositoryPort.findById(resultSet.getLong("status_id")).orElse(null));
                        plane.setModel(planeModelRepositoryPort.findById(resultSet.getLong("model_id")));
                        return Optional.of(plane);
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
                        Plane plane = new Plane();
                        plane.setId(resultSet.getLong("id"));
                        plane.setPlate(resultSet.getString("plate"));
                        plane.setCapacity(resultSet.getInt("capacity"));
                        plane.setFabricationDate(resultSet.getDate("fabrication_date"));
                        plane.setStatus(
                                planeStatusRepositoryPort.findById(resultSet.getLong("status_id")).orElse(null));
                        plane.setModel(planeModelRepositoryPort.findById(resultSet.getLong("model_id")));
                        planes.add(plane);
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
