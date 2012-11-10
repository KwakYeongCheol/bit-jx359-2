package kr.co.webcash.service.comment;

import kr.co.webcash.domain.comment.Comment;
import kr.co.webcash.domain.comment.CommentType;

public interface CommentService {
	void save(Comment comment);
	void update(Comment comment);
	void delete(Comment comment);
	
//	long findLastIdByBlogIdAndTargetIdAndType(String blogId, long targetId, String type);

//	Comment findByIdAndBlogIdAndTargetIdAndType(long id, String blogId, long targetId, String type);

	long getTargetIdByBlogIdAndCommentTypeAndDisplayId(String blogId, CommentType type, long targetDisplayId);
	long findLastDisplayIdByBlogIdAndCommentType(String blogId, CommentType type);
	
//	Comment findByTargetIdAndCommentType(long targetId, CommentType type);
	Comment findByTargetIdAndCommentTypeAndDisplayId(long targetId,	CommentType type, long displayId);
}
