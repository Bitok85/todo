CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS task_category (
    task_id int NOT NULL REFERENCES task(id),
    category_id int NOT NULL REFERENCES  category(id)
);