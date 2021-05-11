DROP DATABASE IF EXISTS distributedSystem;
CREATE DATABASE distributedSystem;
USE distributedSystem;
CREATE TABLE IF NOT EXISTS media (
    media_id int NOT NULL AUTO_INCREMENT,
    media_name VARCHAR(255),
    PRIMARY KEY (media_id)
);

CREATE TABLE IF NOT EXISTS book (
    media_id int,
    author VARCHAR(255),
    FOREIGN KEY (media_id) REFERENCES media(media_id)
);

CREATE TABLE IF NOT EXISTS newspaper (
    media_id int,
    newspaper_type VARCHAR(255),
    FOREIGN KEY (media_id) REFERENCES media(media_id)
);