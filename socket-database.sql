CREATE DATABASE IF NOT EXISTS distributeSocket;
USE distributeSocket;
DROP TABLE IF EXISTS student;
CREATE TABLE IF NOT EXISTS student (
	id integer,
    fullName VARCHAR(255),
    studyYear VARCHAR(255)
);