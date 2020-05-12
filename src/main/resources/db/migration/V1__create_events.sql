CREATE TABLE events (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  hosting_organization VARCHAR(255),
  category_id BIGINT NOT NULL
);

CREATE INDEX events_category_id_index ON events(category_id);