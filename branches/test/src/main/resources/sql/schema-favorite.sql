CREATE TABLE favorites (
  loginId VARCHAR(10) NOT NULL, 
  blogId VARCHAR(100) NOT NULL,
 PRIMARY KEY(loginId, blogId)
);

insert into favorites(loginId, blogId) values(1, 2);
insert into favorites(loginId, blogId) values(2, 1);
