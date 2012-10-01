CREATE TABLE comment (
  id bigint NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  targetId bigint NOT NULL,
  commentType VARCHAR(50) NOT NULL,
  writerId VARCHAR(20) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated timestamp,
  PRIMARY KEY(id, blogId, targetId, commentType)
);