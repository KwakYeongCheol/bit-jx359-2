CREATE TABLE comment (
  id VARCHAR(10) NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  targetId VARCHAR(10) NOT NULL,
  commentType VARCHAR(50) NOT NULL,
  writerId VARCHAR(20) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated date,
  PRIMARY KEY(id, blogId, targetId, commentType)
);



