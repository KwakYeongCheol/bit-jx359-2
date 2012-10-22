package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;

public interface CommentRepository {
	void save(Comment comment);
	void update(Comment comment);
	void delete(Comment comment);

	List<Comment> findAllByTargetIdAndType(long targetId, CommentType type);

//	Comment findLastByTargetIdAndType(long targetId, String type);

	Comment findByDisplayIdAndTargetIdAndType(long displayId, long targetId, String type);
	
	Comment findLastByBlogIdAndCommentType(String blogId, CommentType type);
	Comment findLast();
}
