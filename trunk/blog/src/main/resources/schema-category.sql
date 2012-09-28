CREATE TABLE category (
  id bigint NOT NULL,
  blogId VARCHAR(10) NOT NULL,
  title VARCHAR(50) NOT NULL,
  primary key(id, blogId)
);

insert into category(id, blogId, title) values(1, 1, '2012년 일기');
insert into category(id, blogId, title) values(2, 1, '웹케시 프로젝트');

insert into category(id, blogId, title) values(1, 2, '타이니팜');
insert into category(id, blogId, title) values(2, 2, '디아블로3');