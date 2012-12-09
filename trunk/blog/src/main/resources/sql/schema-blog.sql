CREATE TABLE blogs (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  owner VARCHAR(100)  NOT NULL,
  blogTheme VARCHAR(50) NOT NULL,
  dateCreated timestamp,
  totalCount BIGINT NOT NULL
);

INSERT INTO blogs(id, title, owner, dateCreated, totalCount, blogTheme) VALUES ('1', '곽범생의 블로그', '1', now(), 1024, 'defaultTheme');
INSERT INTO blogs(id, title, owner, dateCreated, totalCount, blogTheme) VALUES ('2', 'Is This Blog?', '2', now(), 551, 'black');

CREATE TABLE blog_widget(
	blogId VARCHAR(10) NOT NULL,
	visitCount VARCHAR(5) NOT NULL,
	contents VARCHAR(5) NOT NULL,
	tag VARCHAR(5) NOT NULL,
	PRIMARY KEY(blogId)
);

INSERT INTO blog_widget(blogId, visitCount, contents, tag) VALUES ('1', 'true', 'true', 'true');
INSERT INTO blog_widget(blogId, visitCount, contents, tag) VALUES ('2', 'true', 'false', 'true');

CREATE TABLE blog_visit_history(
	blogId VARCHAR(10) NOT NULL,
	connectIPAddress VARCHAR(50) NOT NULL,
	visited timestamp NOT NULL
);

INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-18');

INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-18');
INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-18');
INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-18');
INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-18');
INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-18');
INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-18');

INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-17');
INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-17');
INSERT INTO blog_visit_history(blogId, connectIPAddress, visited)
VALUES ('1', '1', '2012-11-17');



