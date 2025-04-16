 -- Table: sales
CREATE TABLE IF NOT EXISTS sales (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    payment_method TEXT NOT NULL,
        sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(uuid)
);

-- Table: sale_products
CREATE TABLE IF NOT EXISTS sale_products (
    sale_id UUID NOT NULL,
    product_id UUID NOT NULL,
    PRIMARY KEY (sale_id, product_id),
    FOREIGN KEY (sale_id) REFERENCES sales(id),
    FOREIGN KEY (product_id) REFERENCES products(uuid)
);

