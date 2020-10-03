CREATE DATABASE systemDB;
USE systemDB;

-- drop database systemDB;

CREATE TABLE championships(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE games(
	id INT NOT NULL AUTO_INCREMENT,
    score INT,
    min_season INT,
    max_season INT,
    min_record INT,
    max_record INT,
    championships_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(championships_id) REFERENCES championships(id)
);

-- SELECT * FROM games;

SET GLOBAL TIME_ZONE = '+3:00';