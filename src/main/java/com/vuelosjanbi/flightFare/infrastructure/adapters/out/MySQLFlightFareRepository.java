package com.vuelosjanbi.flightFare.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.flightFare.application.ports.out.FlightFareRepositoryPort;
import com.vuelosjanbi.flightFare.domain.models.FlightFare;

public class MySQLFlightFareRepository implements FlightFareRepositoryPort {
    private final String url;
    private final String username;
    private final String password;

    public MySQLFlightFareRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public FlightFare save(FlightFare flightFare) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO flight_fare(description, details, value) VALUES(?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightFare.getDescription());
                statement.setString(2, flightFare.getDetails());
                statement.setDouble(3, flightFare.getValue());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightFare;
    }

    public FlightFare update(FlightFare flightFare) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE flight_fare SET description = ?, details = ?, value = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightFare.getDescription());
                statement.setString(2, flightFare.getDetails());
                statement.setDouble(3, flightFare.getValue());
                statement.setLong(4, flightFare.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightFare;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM flight_fare WHERE id= ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<FlightFare> findById(Long id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM flight_fare WHERE id = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        FlightFare flightFare = new FlightFare(
                                resultSet.getString("description"),
                                resultSet.getString("details"),
                                resultSet.getDouble("value"));
                        return Optional.of(flightFare);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<FlightFare> findAll() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM flight_fare";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<FlightFare> flightFares = new ArrayList<>();
                    while (resultSet.next()) {
                        FlightFare flightFare = new FlightFare(
                                resultSet.getLong("id"),
                                resultSet.getString("description"),
                                resultSet.getString("details"),
                                resultSet.getDouble("value"));
                        flightFares.add(flightFare);
                    }
                    return flightFares;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
