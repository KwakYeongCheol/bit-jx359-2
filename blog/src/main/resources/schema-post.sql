CREATE TABLE post (
  id VARCHAR(10) NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  categoryId VARCHAR(10) NOT NULL,
  title VARCHAR(50) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated date,
  PRIMARY KEY(id, blogId)
);

INSERT INTO POST (id, blogId, title, contents, dateCreated, categoryId)
VALUES('1', '1', '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1);

INSERT INTO POST (id, blogId, title, contents, dateCreated, categoryId)
VALUES('2', '1', 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2);


INSERT INTO POST (id, blogId, title, contents, dateCreated, categoryId)
VALUES('1', '2', 'Tiny Farm ', '말하는 피닉스는 안생기나? ', NOW(),1 );

INSERT INTO POST (id, blogId, title, contents, dateCreated, categoryId)
VALUES('2', '2', '해적 시리즈..  ', '더럽게 안나와 ㅠㅠ ', NOW(), 1);

INSERT INTO POST (id, blogId, title, contents, dateCreated, categoryId)
VALUES('3', '2', '전설템 득템  ', '오예 치킨먹자 고고 ', NOW(), 2);
