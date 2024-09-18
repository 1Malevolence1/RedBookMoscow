-- liquibase formatted sql

-- changeset kodi:1
CREATE TABLE animal_obj (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    latin_name VARCHAR(255) NOT NULL,
    division VARCHAR(255),
    family VARCHAR(255),
    status VARCHAR(255),
    distribution TEXT,
    in_habitat TEXT,
    habitat_features TEXT,
    mitigating_factors TEXT,
    protection_measures_taken TEXT,
    changes_in_status_of_species TEXT,
    needed_conservation_actions TEXT,
    sources_of_information TEXT
    authors TEXT
);




