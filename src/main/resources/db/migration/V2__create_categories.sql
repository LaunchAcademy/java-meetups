CREATE TABLE categories (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX categories_name_uniq_index ON categories(name);