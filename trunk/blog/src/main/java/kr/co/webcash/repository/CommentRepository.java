package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;

public interface CommentRepository {

	void save(Comment comment);

	List<Comment> findAllByBlogIdAndTargetIdAndType(String blogId, long targetId, CommentType type);

	Comment findLastByBlogIdAndTargetIdAndType(String blogId, long targetId, String type);

	void delete(Comment comment);

	void update(Comment comment);

	Comment findByIdAndBlogIdAndTargetIdAndType(long id, String blogId, long targetId, String type);

}
