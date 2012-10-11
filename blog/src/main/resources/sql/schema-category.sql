CREATE TABLE category (
  id bigint NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  displayId bigint NOT NULL,
  title VARCHAR(50) NOT NULL,
  primary key(id)
);

insert into category(id, blogId, title, displayId) values(1, '1', '2012년 일기', 1);
insert into category(id, blogId, title, displayId) values(2, '1', '웹케시 프로젝트', 2);

insert into category(id, blogId, title, displayId) values(3, '2', '타이니팜', 1);
insert into category(id, blogId, title, displayId) values(4, '2', '디아블로3', 2);