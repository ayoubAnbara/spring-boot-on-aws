CREATE TABLE product
(
    id             BINARY(16) PRIMARY KEY,
    sku            VARCHAR(16) UNIQUE NOT NULL,
    name           VARCHAR(125)       NOT NULL,
    description    VARCHAR(125),
    price          DECIMAL            NOT NULL,
    created_at     TIMESTAMP          NOT NULL,
    updated_at     TIMESTAMP
);
