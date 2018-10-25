CREATE TABLE IF NOT EXISTS users (
    username    VARCHAR(20) PRIMARY KEY,
    password    VARCHAR(65) NOT NULL,
    first_name  VARCHAR(30) NOT NULL,
    last_name   VARCHAR(30) NOT NULL
);