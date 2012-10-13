CREATE TABLE users (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(50)  NOT NULL
);

INSERT INTO users(id, name, password) VALUES ('1', '곽영철', '43EcDjo6s0CrGUiHIlFSRg==');
INSERT INTO users(id, name, password) VALUES ('2', '민용기', '43EcDjo6s0CrGUiHIlFSRg==');

CREATE TABLE blogs (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  owner VARCHAR(20)  NOT NULL,
  dateCreated timestamp
);

INSERT INTO blogs(id, title, owner) VALUES ('1', '곽범생의 블로그', '1');
INSERT INTO blogs(id, title, owner) VALUES ('2', 'Is This Blog?', '2');
