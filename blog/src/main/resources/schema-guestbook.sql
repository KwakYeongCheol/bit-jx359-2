CREATE TABLE guestbook (
  id VARCHAR(10) NOT NULL,
  writer VARCHAR(20) NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated timestamp NOT NULL,
  PRIMARY KEY(id, blogId)
);

insert into guestbook (id, writer, blogId, contents, dateCreated)
values ('1', '2', '1', '안녕하세요. 들렀다가 갑니다.', now());

insert into guestbook (id, writer, blogId, contents, dateCreated)
values ('2', '1', '1', '공지사항 - 블로그 이사합니다.', now());

insert into guestbook (id, writer, blogId, contents, dateCreated)
values ('1', '1', '2', '안녕하세요. 저도 들렀다가 갑니다.', now());

insert into guestbook (id, writer, blogId, contents, dateCreated)
values ('2', '2', '2', '롤로로로로로롤', now());

