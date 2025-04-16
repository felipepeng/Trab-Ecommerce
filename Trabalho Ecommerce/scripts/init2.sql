CREATE TABLE sales (
                       id UUID PRIMARY KEY,
                       user_id UUID NOT NULL,
                       payment_type VARCHAR(20) NOT NULL,
                       total_amount DOUBLE PRECISION NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE sale_products (
                               sale_id UUID NOT NULL,
                               product_id UUID NOT NULL,
                               quantity INT DEFAULT 1,

                               PRIMARY KEY (sale_id, product_id),
                               FOREIGN KEY (sale_id) REFERENCES sales(id),
                               FOREIGN KEY (product_id) REFERENCES products(id)
);
