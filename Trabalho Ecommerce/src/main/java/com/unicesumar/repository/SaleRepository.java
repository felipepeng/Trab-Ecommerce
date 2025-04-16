package com.unicesumar.repository;

import com.unicesumar.entities.Sale;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SaleRepository implements EntityRepository<Sale> {

    private final Connection connection;
    public SaleRepository(Connection connection){this.connection = connection;}

    @Override
    public void save(Sale entity) {

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
