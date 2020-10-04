CREATE DATABASE systemDB;
USE systemDB;

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
    championships_id INT NOT NULL DEFAULT 0,
    PRIMARY KEY(id),
    FOREIGN KEY(championships_id) REFERENCES championships(id)
);

 SELECT * FROM games;

 SELECT * FROM championships;

-- update systemDB.championships set id = id-1;

-- ALTER TABLE systemDB.championships AUTO_INCREMENT = 1;

-- UPDATE systemDB.championships SET id = 1 WHERE id = 0;

SET SQL_SAFE_UPDATES = 0;

SET GLOBAL TIME_ZONE = '+3:00';