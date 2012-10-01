package kr.co.webcash.service;

import kr.co.webcash.domain.Comment;

public interface CommentService {

	void save(Comment comment);

	long findLastIdByBlogIdAndTargetIdAndType(String blogId, long targetId, String type);

	void delete(Comment comment);

	void update(Comment comment);

	Comment findByIdAndBlogIdAndTargetIdAndType(long id, String blogId, long targetId, String type);
	
}
