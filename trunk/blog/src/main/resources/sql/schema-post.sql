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
  PRIMARY KEY(postId, scrappedBlogId, scrappedPostId)
);

CREATE TABLE post_metadata(
	postId bigint NOT NULL,
	isPublic CHAR(5) NOT NULL,
	canComment CHAR(5) NOT NULL,
	canScrap CHAR(5) NOT NULL,
	canTrackback CHAR(5) NOT NULL,
	PRIMARY KEY(postId)
);

CREATE TABLE post_revision(
	postId bigint NOT NULL,
	displayId bigint NOT NULL,
	diff VARCHAR(2048) NOT NULL,
	PRIMARY KEY(postId, displayId)
);

CREATE TABLE post_tag(
	postId bigint NOT NULL,
	tag_value VARCHAR(100) NOT NULL
);


INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(1, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D<br />한숨대신 함성으로~<br />걱정대신 열정으로~</br /><br />포기대신 죽기 살기로~~<br />', NOW(), 1, 1);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback, canComment)
	VALUES(1, 'true', 'true', 'true', 'true');
INSERT INTO SCRAPS (postId, scrappedBlogId, scrappedPostId, scrappedPostTitle, scrappedPostContents)
	VALUES(1, '2', 1, 'Tiny Farm ', '말하는 피닉스는 안생기나? ');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(1, 1, '');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(2, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 2);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback, canComment)
	VALUES(2, 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(2, 1, '');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(3, 'Tiny Farm ', '말하는 피닉스는 안생기나? ', NOW(),3, 1);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback, canComment)
	VALUES(3, 'true', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(3, 1, '');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(4, '해적 시리즈..  ', '더럽게 안나와 ㅠㅠ ', NOW(), 3, 2);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback, canComment)
	VALUES(4, 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(4, 1, '');

INSERT INTO POST (id,  title, contents, dateCreated, categoryId, displayId)
	VALUES(5, '전설템 득템  ', '오예 치킨먹자 고고 ', NOW(), 4, 3);
INSERT INTO POST_METADATA (postId, isPublic, canScrap, canTrackback, canComment)
	VALUES(5, 'true', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(5, 1, '');


INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(6, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 3);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(6, 1, '');
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(7, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 4);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(7, 1, '');
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(8, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 5);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(8, 1, '');
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(9, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 6);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(9, 1, '');
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(10, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 7);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(10, 1, '');
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(11, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 8);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(11, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(12, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 9);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(12, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(13, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 10);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(13, 1, '');	
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(14, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 11);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(14, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(15, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 12);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(15, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(16, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 13);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(16, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(17, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 14);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(17, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(18, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 15);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(18, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(19, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 16);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(19, 1, '');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(20, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 17);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(20, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(21, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 18);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(21, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(22, '첫 번째 포스팅', '안녕하세요. 곽범생입니다. 첫 포스팅하네요 :D', NOW(), 1, 19);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(22, 1, '');

	