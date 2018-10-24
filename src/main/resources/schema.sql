-- Database: app_data

-- DROP DATABASE app_data;

CREATE DATABASE app_data
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE IF NOT EXISTS users (
    username    VARCHAR(20) PRIMARY KEY,
    password    VARCHAR(65) NOT NULL,
    first_name  VARCHAR(30) NOT NULL,
    last_name   VARCHAR(30) NOT NULL
);