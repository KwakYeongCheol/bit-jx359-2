CREATE TABLE post (
  id VARCHAR(10) NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  title VARCHAR(50) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated date NOT NULL,
  PRIMARY KEY(id, blogId)
);

