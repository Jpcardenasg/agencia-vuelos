package com.vuelosjanbi.customer.infrastructure.adapters.out;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;

import com.vuelosjanbi.documentType.domain.models.DocumentType;

import com.vuelosjanbi.customer.application.ports.out.CustomerRepositoryPort;
import com.vuelosjanbi.customer.domain.models.Customer;

public class MySQLCustomerRepository implements CustomerRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  public MySQLCustomerRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public Customer save(Customer customer) {
    String query = "INSERT INTO customers (id, name, age, document_type_id) VALUES (?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setInt(3, customer.getAge());
        preparedStatement.setLong(4, customer.getDocumentType().getId());
        preparedStatement.executeUpdate();

        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        if (e.getSQLState().equals("23000")) {
          throw new RuntimeException("Duplicate ID error: " + customer.getId(), e);
        } else {
          throw new RuntimeException("Error saving Customer", e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }

    return customer;
  }

  public Customer update(Customer customer) {
    String query;
    if (customer.getDocumentType() == null) {
      query = "UPDATE customers SET name = ?, age = ? WHERE id = ?";
    } else {
      query = "UPDATE customers SET name = ?, age = ?, document_type_id = ? WHERE id = ?";
    }
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      connection.setAutoCommit(false);

      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        if (customer.getDocumentType() == null) {
          preparedStatement.setString(1, customer.getName());
          preparedStatement.setInt(2, customer.getAge());
          preparedStatement.setString(3, customer.getId());
        } else {
          preparedStatement.setString(1, customer.getName());
          preparedStatement.setInt(2, customer.getAge());
          preparedStatement.setLong(3, customer.getDocumentType().getId());
          preparedStatement.setString(4, customer.getId());
        }
        preparedStatement.executeUpdate();

        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        throw new RuntimeException("Error updating Customer", e);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Database connection error", e);
    }

    return customer;
  }

  @Override
  public Optional<Customer> findById(String id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM customers WHERE id = ?";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, id);
        preparedStatement.executeQuery();
        try (ResultSet resultSet = preparedStatement.getResultSet()) {
          if (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getString("id"));
            customer.setName(resultSet.getString("name"));
            customer.setAge(resultSet.getInt("age"));
            return Optional.of(customer);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void deleteById(String id) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "DELETE FROM customers WHERE id = ?";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Customer> findAll() {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "SELECT * FROM customers";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.executeQuery();
        try (ResultSet resultSet = preparedStatement.getResultSet()) {
          List<Customer> customers = new ArrayList<>();
          while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getString("id"));
            customer.setName(resultSet.getString("name"));
            customer.setAge(resultSet.getInt("age"));
            customers.add(customer);
          }
          return customers;
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}
