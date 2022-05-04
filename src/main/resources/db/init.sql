create table users
(
    id BIGSERIAL PRIMARY KEY ,
    username   varchar(128) unique ,
    firstname  varchar(128),
    lastname   varchar(128),
    birth_date DATE,
    role       varchar(32),
    info JSONB,
    company_id INT REFERENCES company(id)
);

create table company
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
);