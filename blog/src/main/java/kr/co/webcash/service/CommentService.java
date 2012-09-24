package kr.co.webcash.service;

import kr.co.webcash.domain.Comment;

public interface CommentService {

	void save(Comment comment);

	int findLastIdByBlogIdAndTargetIdAndType(String blogId, String targetId, String type);

	void delete(Comment comment);

	void update(Comment comment);

	Comment findByIdAndBlogIdAndTargetIdAndType(String id, String blogId, String targetId, String type);
	
}
