CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    name TEXT,
    descr TEXT,
    created TIMESTAMP,
    done BOOLEAN
);