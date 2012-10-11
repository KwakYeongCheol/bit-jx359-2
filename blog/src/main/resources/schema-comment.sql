CREATE TABLE comment (
  id bigint NOT NULL,
  targetId bigint NOT NULL,
  commentType VARCHAR(50) NOT NULL,
  displayId bigint NOT NULL,
  writerId VARCHAR(20) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated timestamp,
  PRIMARY KEY(id)
);