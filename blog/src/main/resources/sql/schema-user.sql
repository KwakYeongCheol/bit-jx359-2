CREATE TABLE users (
  id VARCHAR(10) NOT NULL,
  password VARCHAR(50)  NOT NULL,
  name VARCHAR(40) NOT NULL,
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(20) NOT NULL,
  sex CHAR(6) NOT NULL,
  birthday TIMESTAMP NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO users(id, name, password, firstName, lastName, sex, birthday) 
	VALUES ('1', '곽영철', '43EcDjo6s0CrGUiHIlFSRg==', '영철', '곽', 'male', now());
INSERT INTO users(id, name, password, firstName, lastName, sex, birthday) 
	VALUES ('2', '민용기', '43EcDjo6s0CrGUiHIlFSRg==', '용기', '민', 'male', now());

CREATE TABLE blogs (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  owner VARCHAR(20)  NOT NULL,
  dateCreated timestamp
);

INSERT INTO blogs(id, title, owner) VALUES ('1', '곽범생의 블로그', '1');
INSERT INTO blogs(id, title, owner) VALUES ('2', 'Is This Blog?', '2');
