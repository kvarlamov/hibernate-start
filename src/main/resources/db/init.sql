drop table users;

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

create table profile
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users (id),
    street VARCHAR(128),
    language CHAR(2)
);

CREATE TABLE users_chat
(
    id BIGSERIAL primary key ,
    user_id BIGINT REFERENCES users(id),
    chat_id BIGINT REFERENCES chat(id),
    created_at TIMESTAMP NOT NULL ,
    created_by VARCHAR(128) NOT NULL,
    UNIQUE (user_id, chat_id)
);

DROP TABLE users_chat;

create table chat
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
);

create table company
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
);

select *
from users;