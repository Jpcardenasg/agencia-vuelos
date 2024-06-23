package com.vuelosjanbi.city.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.city.application.ports.CityRepositoryPort;
import com.vuelosjanbi.city.domain.models.City;

public class MySQLCityRepository implements CityRepositoryPort {

    private final String url;
    private final String username;
    private final String password;

    public MySQLCityRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    @Override
    public City save(City city) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query;
            if (city.getCountry() == null) {
                query = "INSERT INTO city (name) VALUES (?)";
            } else {
                query = "INSERT INTO city (name, country_id) VALUES (?, ?)";
            }
            try (PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, city.getName());
                if (city.getCountry() != null) {
                    statement.setLong(2, city.getCountry().getId());
                }
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        city.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public City update(City city) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query;
            if (city.getCountry() == null) {
                query = "UPDATE city SET name = ? WHERE id = ?";
            } else {
                query = "UPDATE city SET name = ?, country_id = ? WHERE id = ?";
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getName());
                if (city.getCountry() != null) {
                    statement.setLong(2, city.getCountry().getId());
                    statement.setLong(3, city.getId());
                } else {
                    statement.setLong(3, city.getId());
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public void deleteById(Long cityId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM city WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, cityId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM city";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        City city = new City();
                        city.setId(resultSet.getLong("id"));
                        city.setName(resultSet.getString("name"));
                        cities.add(city);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public Optional<City> findById(Long cityId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM city WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, cityId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        City city = new City();
                        city.setId(resultSet.getLong("id"));
                        city.setName(resultSet.getString("name"));
                        return Optional.of(city);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<City> findCitiesByCountryId(Long countryId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM city WHERE country_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, countryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<City> cities = new ArrayList<>();
                    while (resultSet.next()) {
                        City city = new City();
                        city.setId(resultSet.getLong("id"));
                        city.setName(resultSet.getString("name"));
                        cities.add(city);
                    }
                    return cities;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
