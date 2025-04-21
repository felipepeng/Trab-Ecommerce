package com.unicesumar.repository;

import com.unicesumar.entities.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SaleRepository implements EntityRepository<Sale> {

    private final Connection connection;
    private final Sale_ProductsRepository Sale_productRepo;

    public SaleRepository(Connection connection){
        this.connection = connection;
        this.Sale_productRepo = new Sale_ProductsRepository(connection);
    }

    @Override
    public void save(Sale entity) {
        String query = "INSERT INTO sales VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setObject(1, entity.getUuid().toString());
            stmt.setObject(2, entity.getUser().getUuid().toString());
            stmt.setString(3, entity.getPaymentType().toString());
            stmt.setTimestamp(4, Timestamp.valueOf(entity.getPaymentDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Sale_productRepo.save(entity);
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
