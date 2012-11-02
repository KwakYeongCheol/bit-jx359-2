CREATE TABLE blogs (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  owner VARCHAR(20)  NOT NULL,
  dateCreated timestamp,
  totalCount BIGINT NOT NULL
);

INSERT INTO blogs(id, title, owner, dateCreated, totalCount) VALUES ('1', '곽범생의 블로그', '1', now(), 1024);
INSERT INTO blogs(id, title, owner, dateCreated, totalCount) VALUES ('2', 'Is This Blog?', '2', now(), 551);

CREATE TABLE blog_visit_history(
	blogId VARCHAR(10) NOT NULL,
	connectIPAddress VARCHAR(50) NOT NULL,
	visited timestamp NOT NULL
);

