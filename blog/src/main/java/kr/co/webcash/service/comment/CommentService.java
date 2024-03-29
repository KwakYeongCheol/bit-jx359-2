package kr.co.webcash.service.comment;

import java.util.List;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.comment.Comment;
import kr.co.webcash.domain.comment.CommentType;
import kr.co.webcash.domain.post.Post;

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
	List<Comment> listByBlogIdAndPageNumberAndPageSize(String blogId, int pageNumber, int pageSize);
	
	int countByBlogId(String blogId);
	List<Comment> listByBlogIdAndPage(String blogId, Page page);
	void delete(Post post);
}
