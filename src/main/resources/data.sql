DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

INSERT INTO user (id, username, password) VALUES
  ('1', 'admin', '$2a$04$jJKpMWsV56JaGoJKm6/GjuZTUcsBxJjyFWNLpQqsSKD6Qtwx8is6i');