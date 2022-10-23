CREATE TABLE if not exists priorities (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    position int
);

CREATE TABLE if not exists users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    login TEXT UNIQUE,
    password TEXT
);

CREATE TABLE if not exists task (
    id SERIAL PRIMARY KEY,
    name TEXT,
    descr TEXT,
    created TIMESTAMP,
    done BOOLEAN,
    user_id int REFERENCES users(id),
    priority_id int REFERENCES priorities(id)
);
