CREATE TABLE movies(
  id       INT          NOT NULL,
  version  INT          NOT NULL  DEFAULT 0,
  title    VARCHAR(255) NOT NULL,
  watched  VARCHAR(10)  NULL,
  rating   VARCHAR(10)          NULL,
  movie_length   INT          NULL,
  PRIMARY KEY (id));
