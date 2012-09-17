CREATE TABLE users (
  id VARCHAR(10) NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(20)  NOT NULL
);

INSERT INTO users(id, name, password) VALUES ('1', '김태호', '1');
INSERT INTO users(id, name, password) VALUES ('2', '이희권', '2');