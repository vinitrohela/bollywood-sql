IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[movies]') AND type in (N'U'))

BEGIN
CREATE TABLE movies(
  id       INT          NOT NULL IDENTITY(1,1),
  version  INT          NOT NULL  DEFAULT 0,
  title    VARCHAR(255) NOT NULL,
  watched  VARCHAR(10)  NULL,
  rating   VARCHAR(10)          NULL,
  movie_length   INT          NULL,
  PRIMARY KEY (id));
END
