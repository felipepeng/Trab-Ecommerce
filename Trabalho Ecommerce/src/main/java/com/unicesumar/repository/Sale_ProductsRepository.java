package com.unicesumar.repository;

import com.unicesumar.entities.Product;
import com.unicesumar.entities.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Sale_ProductsRepository implements EntityRepository<Sale> {

    private final Connection connection;
    public Sale_ProductsRepository(Connection connection){this.connection = connection;}

    @Override
    public void save(Sale entity) {
        String query = "INSERT INTO sale_products VALUES (?, ?)";

        try {
            System.out.println("Teste3");
            PreparedStatement stmt = this.connection.prepareStatement(query);
            for (Product product : entity.getProducts()) {
            stmt.setObject(1, entity.getUuid());
            stmt.setObject(2, product.getUuid());
            stmt.addBatch();
            }
            System.out.println("Teste4");
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Sale> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Sale> findAll() {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
