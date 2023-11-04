CREATE TABLE customers
(
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    phone_number VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE musicals
(
    id                  INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title               VARCHAR(255),
    description         LONG VARCHAR,
    theater_name        VARCHAR(255),
    thumbnail_image_url VARCHAR(255),
    duration_in_seconds INT
);

CREATE TABLE show_times
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    musical_id INT,
    FOREIGN KEY (musical_id) REFERENCES musicals (id)
);

CREATE TABLE ticket_types
(
    id    INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name  VARCHAR(255),
    price decimal
);

CREATE TABLE tickets
(
    id             INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_id    int,
    musical_id     int,
    showtime_id    int,
    seat_number    int,
    ticket_type_id int
);

