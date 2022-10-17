CREATE TABLE if not exists task (
    id SERIAL PRIMARY KEY,
    name TEXT,
    descr TEXT,
    created TIMESTAMP,
    done BOOLEAN
);

CREATE TABLE if not exists users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    login TEXT UNIQUE,
    password TEXT
);

--ALTER TABLE task ADD column user_id int REFERENCES user(id);