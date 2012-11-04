package kr.co.webcash.service;

import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;
import kr.co.webcash.domain.User;

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
	void sendNotification(User loginUser, String blogId, long targetDisplayId, CommentType commentType);
	
}
