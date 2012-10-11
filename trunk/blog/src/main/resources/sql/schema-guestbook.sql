CREATE TABLE guestbook (
  id bigint NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  displayId bigint NOT NULL,
  writer VARCHAR(20) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated timestamp NOT NULL,
  PRIMARY KEY(id)
);

insert into guestbook (id, writer, blogId, contents, dateCreated, displayId)
values (1, '2', '1', '안녕하세요. 들렀다가 갑니다.', now(), 1);

insert into guestbook (id, writer, blogId, contents, dateCreated, displayId)
values (2, '1', '1', '공지사항 - 블로그 이사합니다.', now(), 2);

insert into guestbook (id, writer, blogId, contents, dateCreated, displayId)
values (3, '1', '2', '안녕하세요. 저도 들렀다가 갑니다.', now(), 1);

insert into guestbook (id, writer, blogId, contents, dateCreated, displayId)
values (4, '2', '2', '롤로로로로로롤', now(), 2);

