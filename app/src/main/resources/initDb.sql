CREATE TABLE users
(
    id        INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password  VARCHAR(255)        NOT NULL,
    role      VARCHAR(10)         NOT NULL CHECK (role IN ('ADMIN', 'USER'))
);