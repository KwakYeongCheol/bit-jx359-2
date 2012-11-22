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
  targetPostId bigint NOT NULL,
  targetPostRevisionId bigint NOT NULL,
  PRIMARY KEY(postId, targetPostId)
);

CREATE TABLE post_metadata(
	postId bigint NOT NULL,
	isPublic CHAR(5) NOT NULL,
	isTemp CHAR(5) NOT NULL,
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
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(1, 'true', 'false', 'true', 'true', 'true');
INSERT INTO SCRAPS (postId, targetPostId, targetPostRevisionId)
	VALUES(1, 3, 1);
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(1, 1, '');
INSERT INTO post_tag (postId, tag_value)
	VALUES(1, '자바');
INSERT INTO post_tag (postId, tag_value)
	VALUES(1, '애플');
INSERT INTO post_tag (postId, tag_value)
	VALUES(1, '노트북');
INSERT INTO post_tag (postId, tag_value)
	VALUES(1, '제주도');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(2, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 2);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(2, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(2, 1, '');
INSERT INTO post_tag (postId, tag_value)
	VALUES(2, '자바');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(3, 'Tiny Farm ', '말하는 피닉스는 안생기나? ', NOW(),3, 1);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(3, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(3, 1, '');
INSERT INTO post_tag (postId, tag_value)
	VALUES(3, '애플');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(4, '해적 시리즈..  ', '더럽게 안나와 ㅠㅠ ', NOW(), 3, 2);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(4, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(4, 1, '');
INSERT INTO post_tag (postId, tag_value)
	VALUES(4, '안드로이드');

INSERT INTO POST (id,  title, contents, dateCreated, categoryId, displayId)
	VALUES(5, '전설템 득템  ', '오예 치킨먹자 고고 ', NOW(), 4, 3);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(5, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(5, 1, '');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(6, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 3);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(6, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(6, 1, '');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(7, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 4);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(7, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(7, 1, '');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(8, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 5);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(8, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(8, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(9, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 6);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(9, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(9, 1, '');

INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(10, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 7);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(10, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(10, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(11, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 8);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(11, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(11, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(12, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 9);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(12, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(12, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(13, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 10);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(13, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(13, 1, '');
INSERT INTO post_tag (postId, tag_value)
	VALUES(13, '제주도');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(14, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 11);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(14, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(14, 1, '');
	
INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(15, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 12);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(15, 'true', 'false', 'false', 'false', 'false');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(15, 1, '');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(16, 'Its Raining Day.. ', '자바 Java비가 오는 길에 ~ ', NOW(), 2, 13);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(16, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(16, 1, '');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(17, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 14);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(17, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(17, 1, '');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(18, 'Its Raining Day.. ', 'Java 오는 길에 ~ ', NOW(), 2, 15);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(18, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(18, 1, '');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(19, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 16);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(19, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(19, 1, '');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(20, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 17);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(20, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(20, 1, '');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(21, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 18);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(21, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(21, 1, '');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(22, 'Its Raining Day..  임시ㅋ', '숏', NOW(), 2, 19);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(22, 'true', 'true', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(22, 1, '');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(23, 'Its Raining Day.. ', '비가 오는 길에 ~ ', NOW(), 2, 20);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(23, 'true', 'false', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(23, 1, '');
INSERT INTO post_tag (postId, tag_value)
	VALUES(1, '1박2일');
	
	INSERT INTO POST (id, title, contents, dateCreated, categoryId, displayId)
	VALUES(24, 'Its Raining Day..  임시ㅋzzz', '짧아 자바 Java 아이폰 iPhone 안드로이드 슈퍼맨 ㅋㅋㅋ', NOW(), 2, 21);
INSERT INTO POST_METADATA (postId, isPublic, isTemp, canScrap, canTrackback, canComment)
	VALUES(24, 'true', 'true', 'true', 'true', 'true');
INSERT INTO POST_REVISION (postId, displayId, diff)
	VALUES(24, 1, '');