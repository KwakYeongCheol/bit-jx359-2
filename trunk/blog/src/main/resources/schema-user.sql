CREATE TABLE users (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(50)  NOT NULL
);

INSERT INTO users(id, name, password) VALUES ('1', '곽영철', '84c352717eee43ed');
INSERT INTO users(id, name, password) VALUES ('2', '민용기', 'ca5fb0a14fb33507');

CREATE TABLE blogs (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  owner VARCHAR(20)  NOT NULL,
  dateCreated DATE
);

INSERT INTO blogs(id, title, owner) VALUES ('1', '곽범생의 블로그', '1');
INSERT INTO blogs(id, title, owner) VALUES ('2', 'Is This Blog?', '2');
