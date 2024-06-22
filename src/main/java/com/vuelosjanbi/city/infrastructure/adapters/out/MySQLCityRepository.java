package com.vuelosjanbi.city.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.city.application.ports.CityRepositoryPort;
import com.vuelosjanbi.city.domain.models.City;
import com.vuelosjanbi.country.application.ports.out.CountryRepositoryPort;

public class MySQLCityRepository implements CityRepositoryPort {

    private final String url;
    private final String username;
    private final String password;

    @Autowired
    private CountryRepositoryPort countryRepositoryPort;

    public MySQLCityRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    @Override
    public City save(City city) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO city (name,country_id) VALUES(?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getName());
                statement.setLong(2, city.getCountry().getId());
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
                        city.setCountry(countryRepositoryPort.findById(resultSet.getLong("country_id")).orElse(null));
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
                        city.setCountry(countryRepositoryPort.findById(resultSet.getLong("country_id")).orElse(null));
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
