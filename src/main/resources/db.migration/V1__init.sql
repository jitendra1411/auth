CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS user_account(
    id SERIAL not null PRIMARY KEY,
    username VARCHAR,
    password VARCHAR,
    email VARCHAR,
    email_verified BOOLEAN,
    phone VARCHAR,
    phone_verified BOOLEAN,
    status VARCHAR,
    updated_by INTEGER,
    created_on TIMESTAMP WITHOUT TIME ZONE,
    last_updated_on TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS role(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR,
    status VARCHAR,
    updated_by INTEGER,
    created_on TIMESTAMP WITHOUT TIME ZONE,
    last_updated_on TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS user_role(
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INTEGER,
    role_id INTEGER,
    updated_by INTEGER,
    created_on TIMESTAMP WITHOUT TIME ZONE,
    last_updated_on TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

INSERT INTO role (name, status, created_on, last_updated_on) VALUES ('ADMIN', 'ACTIVE', now(), now());
INSERT INTO role (name, status, created_on, last_updated_on) VALUES ('CANDIDATE', 'ACTIVE', now(), now());
INSERT INTO role (name, status, created_on, last_updated_on) VALUES ('RECRUITER', 'ACTIVE', now(), now());
INSERT INTO role (name, status, created_on, last_updated_on) VALUES ('INTERVIEWER', 'ACTIVE', now(), now());
INSERT INTO role (name, status, created_on, last_updated_on) VALUES ('TECH', 'ACTIVE', now(), now());
INSERT INTO role (name, status, created_on, last_updated_on) VALUES ('CLIENT', 'ACTIVE', now(), now());