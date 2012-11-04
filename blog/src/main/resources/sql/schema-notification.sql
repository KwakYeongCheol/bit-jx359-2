CREATE TABLE notification (
  id bigint NOT NULL,
  blogId VARCHAR(50) NOT NULL,
  contents VARCHAR(1024) NOT NULL,
  uri VARCHAR(50) NOT NULL,
  dateCreated timestamp NOT NULL,
  PRIMARY KEY(id)
);