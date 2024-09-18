-- liquibase formatted sql

-- changeset kodi:1
CREATE TABLE view (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL
);


-- changeset kodi:2
CREATE TABLE image_model (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    original_file_name VARCHAR(255),
    size INTEGER,
    content_type VARCHAR(255),
    data bytea
);


-- changeset kodi:3
CREATE TABLE entry_model (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    latin_name VARCHAR(255) NOT NULL,
    division VARCHAR(255),
    family VARCHAR(255),
    status VARCHAR(255),
    distribution VARCHAR(255),
    in_habitat VARCHAR(255),
    habitat_features VARCHAR(255),
    mitigating_factors VARCHAR(255),
    protection_measures_taken VARCHAR(255),
    changes_in_status_of_species VARCHAR(255),
    needed_conservation_actions VARCHAR(255),
    sources_of_information VARCHAR(255),
    authors VARCHAR(255),
    image_id BIGINT,
    view_id BIGINT,
    CONSTRAINT fk_image FOREIGN KEY (image_id) REFERENCES image_model (id) ON DELETE CASCADE,
    CONSTRAINT fk_view FOREIGN KEY (view_id) REFERENCES view (id) ON DELETE CASCADE
);





