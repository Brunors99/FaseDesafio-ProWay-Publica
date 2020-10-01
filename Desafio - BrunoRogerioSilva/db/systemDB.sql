CREATE DATABASE systemDB;
USE systemDB;

CREATE TABLE championships(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE games(
	id INT NOT NULL AUTO_INCREMENT,
    nGame INT NOT NULL,
    score INT,
    min_season INT,
    max_season INT,
    min_record INT,
    max_record INT,
    championships_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(championships_id) REFERENCES championships(id)
);

SET GLOBAL TIME_ZONE = '+3:00';

SET SQL_SAFE_UPDATES = 0;