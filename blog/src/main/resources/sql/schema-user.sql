CREATE TABLE users (
  id VARCHAR(50) NOT NULL,
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