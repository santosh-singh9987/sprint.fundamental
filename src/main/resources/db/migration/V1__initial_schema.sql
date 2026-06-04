CREATE TABLE myuser (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    age INTEGER,
    password VARCHAR(255) NOT NULL,
    blood_group_type VARCHAR(20),
    role VARCHAR(50) NOT NULL
);

CREATE INDEX idx_myuser_email
ON myuser(email);

CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price NUMERIC(12,2) NOT NULL,
    sku VARCHAR(50) NOT NULL UNIQUE
);

CREATE INDEX idx_product_sku
ON product(sku);

CREATE TABLE refresh_token (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP NOT NULL
);

CREATE INDEX idx_refresh_token
ON refresh_token(token);

CREATE TABLE order_management (
    id BIGSERIAL PRIMARY KEY,
    sku VARCHAR(255),
    quantity INTEGER NOT NULL,
    price NUMERIC(12,2) NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT fk_order_user
        FOREIGN KEY(user_id)
        REFERENCES myuser(id)
);

CREATE INDEX idx_order_user
ON order_management(user_id);