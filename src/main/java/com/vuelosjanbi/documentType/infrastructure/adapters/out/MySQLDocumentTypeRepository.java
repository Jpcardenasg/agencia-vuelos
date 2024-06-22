package com.vuelosjanbi.documentType.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.documentType.domain.models.DocumentType;
import com.vuelosjanbi.documentType.application.ports.out.DocumentTypeRepositoryPort;

public class MySQLDocumentTypeRepository implements DocumentTypeRepositoryPort {

    private final String url;
    private final String username;
    private final String password;

    public MySQLDocumentTypeRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public DocumentType save(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO document_type VALUES(?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, documentType.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentType;
    }

    public DocumentType update(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE document_type SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, documentType.getName());
                statement.setLong(2, documentType.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentType;
    }

    @Override
    public void deleteById(Long documentTypeId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM document_type WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, documentTypeId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<DocumentType> findById(Long documentTypeId) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM documentType WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, documentTypeId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        DocumentType documentType = new DocumentType(
                                resultSet.getLong("id"),
                                resultSet.getString("name"));
                        return Optional.of(documentType);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<DocumentType> findByName(String documentTypeName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM document_type WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, documentTypeName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        DocumentType documentType = new DocumentType(
                                resultSet.getLong("id"),
                                resultSet.getString("name"));
                        return Optional.of(documentType);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<DocumentType> findAll() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM document_type";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<DocumentType> countries = new ArrayList<>();
                    while (resultSet.next()) {
                        DocumentType documentType = new DocumentType(
                                resultSet.getLong("id"),
                                resultSet.getString("name"));
                        countries.add(documentType);
                    }
                    return countries;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
