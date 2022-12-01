CREATE TABLE user(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
password VARCHAR(255),
role VARCHAR(255),
PRIMARY KEY (id)
);

INSERT INTO user (name,password,role) VALUES
('Juan','$2a$10$jvYQzZLFDbfApv0.DiAhBunv/SvIkFbsAy6jbOZbLd0scEjfnAO2S','USER'),
('JefeDeJuan','$2a$10$w.PYVTQtWrID53g98WpKn.j3cyBpKDAgJAfkx5LA.dY4NWqLCXmQG','ADMIN'),
('CompañeroDeJuan','$2a$10$9LcWa4OQbpr1K2EaPFZ/uOJhH50bBw0iEgh6rEpAm6Ome5EPmYrNa','CONTRIBUTOR');

