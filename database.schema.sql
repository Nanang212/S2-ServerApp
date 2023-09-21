CREATE DATABASE serverapp;

USE serverapp;

DROP TABLE students;

CREATE TABLE students
(
    id    INTEGER PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(256) NOT NULL,
    email VARCHAR(512) NOT NULL UNIQUE
);