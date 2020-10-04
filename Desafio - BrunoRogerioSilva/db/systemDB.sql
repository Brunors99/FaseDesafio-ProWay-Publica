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
    PRIMARY KEY(id)
);

ALTER TABLE systemDB.games ADD CONSTRAINT championships_id FOREIGN KEY (championships_id) REFERENCES championships(id) ON DELETE CASCADE;

SET SQL_SAFE_UPDATES = 0;

SET GLOBAL TIME_ZONE = '+3:00';

/*-------------------------------------------------------------------------------------------------------------*/


SELECT championships_id FROM systemDB.games JOIN championships ON games.championships_id = championships.id;






