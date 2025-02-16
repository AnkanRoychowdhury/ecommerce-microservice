CREATE TABLE IF NOT EXISTS category
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name               VARCHAR(255),
    description        VARCHAR(255),
    available_quantity DOUBLE PRECISION,
    price              DECIMAL,
    category_id        BIGINT,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;