package com.vuelosjanbi.country.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.country.domain.models.Country;
import com.vuelosjanbi.country.application.ports.out.CountryRepositoryPort;

public class MySQLCountryRepository implements CountryRepositoryPort {

    private final String url;
    private final String username;
    private final String password;

    public MySQLCountryRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Country save(Country country) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO country VALUES(?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    public Country update(Country country) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE country SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getName());
                statement.setLong(2, country.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public void deleteById(Long countryId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, countryId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Country> findById(Long countryId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, countryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Country country = new Country(
                                resultSet.getLong("id"),
                                resultSet.getString("name"));
                        return Optional.of(country);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Country> findByName(String countryName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM country WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, countryName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Country country = new Country(
                                resultSet.getLong("id"),
                                resultSet.getString("name"));
                        return Optional.of(country);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM country";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Country country = new Country(
                                resultSet.getLong("id"),
                                resultSet.getString("name"));
                        countries.add(country);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
