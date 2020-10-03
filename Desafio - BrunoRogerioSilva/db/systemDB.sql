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
    championships_id INT NOT NULL DEFAULT 0,
    PRIMARY KEY(id),
    FOREIGN KEY(championships_id) REFERENCES championships(id)
);
-- ALTER TABLE games CHANGE COLUMN championships_id championships_id INT NOT NULL DEFAULT 0; 

-- select id from games where id = championships_id;

-- SELECT * FROM games;

-- SELECT * FROM championships;

SELECT id FROM systemDB.championships WHERE name='Campeonato da China';

SET GLOBAL TIME_ZONE = '+3:00';