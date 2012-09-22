CREATE TABLE favorites (
  loginId VARCHAR(10) NOT NULL, 
  favoriteBlogId VARCHAR(100) NOT NULL,
 PRIMARY KEY(loginId, favoriteBlogId)
);

insert into favorites(loginId, favoriteBlogId) values(1, 2);
insert into favorites(loginId, favoriteBlogId) values(2, 1);
