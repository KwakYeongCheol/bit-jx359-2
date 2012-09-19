CREATE TABLE visitor (
  id VARCHAR(10) NOT NULL,
  writer VARCHAR(20) NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated date NOT NULL,
  PRIMARY KEY(id, blogId)
);

