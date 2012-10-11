CREATE TABLE post (
  id bigint NOT NULL,
  categoryId bigint NOT NULL,
  displayId bigint NOT NULL,
  title VARCHAR(50) NOT NULL,
  contents VARCHAR(512) NOT NULL,
  dateCreated timestamp,
  PRIMARY KEY(id)
);

CREATE TABLE scraps (
  postId bigint NOT NULL,
  scrappedBlogId VARCHAR(10) NOT NULL,
  scrappedPostId bigint NOT NULL,
  scrappedPostTitle VARCHAR(50) NOT NULL,
  scrappedPostContents VARCHAR(1024) NOT NULL,
  PRIMARY KEY(postId)
);

CREATE TABLE post_metadata(
	postId bigint NOT NULL,
	isPublic CHAR(5) NOT NULL,
	canScrap CHAR(5) NOT NULL,
	canTrackback CHAR(5) NOT NULL,
	PRIMARY KEY(postId)
);


INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(1, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 1);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback)
	VALUES(1, 'true', 'true', 'true');
INSERT INTO SCRAPS (postId, scrappedBlogId, scrappedPostId, scrappedPostTitle, scrappedPostContents)
	VALUES(1, '2', 1, 'Tiny Farm ', '말하는 피닉스는 안생기나? ');


INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(2, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 2);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback)
	VALUES(2, 'false', 'false', 'false');


INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(3, 'Tiny Farm ', '말하는 피닉스는 안생기나? ', NOW(),3, 1);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback)
	VALUES(3, 'true', 'true', 'true');


INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(4, '해적 시리즈..  ', '더럽게 안나와 ㅠㅠ ', NOW(), 3, 2);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback)
	VALUES(4, 'false', 'true', 'true');


INSERT INTO POST (id,  title, contents, dateCreated, categoryId, displayId)
	VALUES(5, '전설템 득템  ', '오예 치킨먹자 고고 ', NOW(), 4, 3);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback)
	VALUES(5, 'true', 'true', 'true');

