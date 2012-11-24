CREATE TABLE notification (
  id bigint NOT NULL,
  blogId VARCHAR(50) NOT NULL,
  contents VARCHAR(1024) NOT NULL,
  dateCreated timestamp NOT NULL,
  isPublic CHAR(5) NOT NULL,
  PRIMARY KEY(id)
);