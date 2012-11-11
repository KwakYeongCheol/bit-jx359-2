CREATE TABLE category (
  id bigint NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  displayId bigint NOT NULL,
  title VARCHAR(50) NOT NULL,
  orderValue bigint NOT NULL,
  PRIMARY KEY(id),
  UNIQUE(blogId, displayId)
);

insert into category(id, blogId, title, displayId, orderValue) values(5, '1', '분류 없음', 1, 0);
insert into category(id, blogId, title, displayId, orderValue) values(1, '1', '2012년 일기', 2, 100000000);
insert into category(id, blogId, title, displayId, orderValue) values(2, '1', '웹케시 프로젝트', 3, 200000000);

insert into category(id, blogId, title, displayId, orderValue) values(6, '2', '분류 없음', 1, 0);
insert into category(id, blogId, title, displayId, orderValue) values(3, '2', '타이니팜', 2, 200000000);
insert into category(id, blogId, title, displayId, orderValue) values(4, '2', '디아블로3', 3, 100000000);