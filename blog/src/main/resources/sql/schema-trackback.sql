CREATE TABLE trackback (
  blogId VARCHAR(10) NOT NULL,
  postId bigint NOT NULL,
  url VARCHAR(100) NOT NULL,
  blogName VARCHAR(100) NOT NULL,
  title VARCHAR(50) NOT NULL,
  excerpt VARCHAR(512) NOT NULL,
  dateCreated timestamp
);



