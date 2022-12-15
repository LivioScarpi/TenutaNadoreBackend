SELECT 'CREATE DATABASE tenutanadore'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'tenutanadore')\gexec

\c tenutanadore;

-- CREATE TABLE accounts (
-- 	user_id serial PRIMARY KEY,
-- 	username VARCHAR ( 50 ) UNIQUE NOT NULL,
-- 	password VARCHAR ( 50 ) NOT NULL,
-- 	email VARCHAR ( 255 ) UNIQUE NOT NULL,
-- 	created_on TIMESTAMP NOT NULL,
--         last_login TIMESTAMP 
-- );