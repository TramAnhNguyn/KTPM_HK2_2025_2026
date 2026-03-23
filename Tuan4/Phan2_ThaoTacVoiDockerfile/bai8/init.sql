CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
INSERT INTO users (name) VALUES ('Nguyen Van A'), ('Tran Thi B');