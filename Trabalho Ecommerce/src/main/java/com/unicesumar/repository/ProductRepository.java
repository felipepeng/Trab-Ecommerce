package com.unicesumar.repository;

import com.unicesumar.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository implements EntityRepository<Product> {
    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Product entity) {
        String query = "INSERT INTO products VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, entity.getUuid().toString());
            stmt.setString(2, entity.getName());
            stmt.setDouble(3, entity.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findById(UUID id) {
        String query = "SELECT * FROM products WHERE uuid = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, id.toString());
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(
                        UUID.fromString(resultSet.getString("uuid")),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                );
                return Optional.of(product);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            throw new RuntimeException("Erro ao buscar produto por ID",e);
        }

        return Optional.empty();

    }

    public List<Product> findByIds(List<UUID> ids) {
        if (ids.isEmpty()) return Collections.emptyList();

        // Monta a string com os "?" do SQL dinamicamente
        String placeholders = ids.stream().map(id -> "?").collect(Collectors.joining(", "));
        String query = "SELECT * FROM products WHERE uuid IN (" + placeholders + ")";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            for (int i = 0; i < ids.size(); i++) {
                stmt.setString(i + 1, ids.get(i).toString());
            }

            ResultSet rs = stmt.executeQuery();
            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product(
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos por IDs", e);
        }
    }


    @Override
    public List<Product> findAll() {
        String query = "SELECT * FROM products";
        List<Product> products = new LinkedList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(
                        UUID.fromString(resultSet.getString("uuid")),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;

    }

    @Override
    public void deleteById(UUID id) {
        String query = "DELETE FROM products WHERE uuid = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
