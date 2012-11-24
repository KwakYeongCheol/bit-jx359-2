CREATE TABLE notification (
  id bigint NOT NULL,
  blogId VARCHAR(50) NOT NULL,
  contents VARCHAR(1024) NOT NULL,
  dateCreated timestamp NOT NULL,
  isPublic CHAR(5) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO notification (id, blogId, contents, dateCreated, isPublic)
VALUES(1, '1', '공개 알림 샘플 ######', now(), 'true')

INSERT INTO notification (id, blogId, contents, dateCreated, isPublic)
VALUES(2, '1', '이것도 공개 알림 샘플 ######', now(), 'true')

INSERT INTO notification (id, blogId, contents, dateCreated, isPublic)
VALUES(3, '1', '이건 비공개 알림 샘플 ######', now(), 'false')

INSERT INTO notification (id, blogId, contents, dateCreated, isPublic)
VALUES(4, '1', '이것도 공개 알림 샘플 ###### 이지롱 ㅋㅋ', now(), 'true')

INSERT INTO notification (id, blogId, contents, dateCreated, isPublic)
VALUES(5, '1', '비공개... 흐규ㅜ흐규 알림 샘플 ######', now(), 'false')