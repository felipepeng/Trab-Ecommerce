
CREATE TABLE products (
    uuid UUID PRIMARY KEY,       -- ID único para cada produto
    name VARCHAR(255) NOT NULL,  -- Nome do produto
    price NUMERIC(10, 2) NOT NULL -- Preço do produto com até 10 dígitos e 2 casas decimais
);

CREATE TABLE users (
    uuid UUID PRIMARY KEY, -- ID único para cada user
    name TEXT NOT NULL, -- Nome do produto
    email TEXT NOT NULL UNIQUE,-- Email da pessoa
    password TEXT NOT NULL -- Senha do User
);
