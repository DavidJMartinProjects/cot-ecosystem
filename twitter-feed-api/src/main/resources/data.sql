DROP TABLE IF EXISTS tweet;

CREATE TABLE tweet (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  created_at VARCHAR(250) NOT NULL,
  text VARCHAR(1000) NOT NULL,
  place VARCHAR(250) DEFAULT NULL,
  user_ VARCHAR(500) DEFAULT NULL
);
