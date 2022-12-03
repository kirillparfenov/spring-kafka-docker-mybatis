-- liquibase formatted sql

-- changeset kirill-parfenov:create-kafka-table
CREATE TABLE IF NOT EXISTS kafka_users (
    id          UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name        varchar
);
grant all privileges on kafka_users to kafka_user;
