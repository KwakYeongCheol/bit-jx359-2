package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImp implements CommentRepository {

	@Autowired SqlSession sqlSession;
	
	@Override
	public void save(Comment comment) {
		sqlSession.insert("Comment.insert",comment);
	}
	
	@Override
	public void delete(Comment comment) {		
		sqlSession.delete("Comment.delete",comment);
	}
	
	@Override
	public void update(Comment comment) {
		sqlSession.update("Comment.update",comment);
	}

	@Override
	public List<Comment> findAllByTargetIdAndType(long targetId, CommentType type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("targetId", targetId);
		params.put("type", type.toString());
		
		return sqlSession.selectList("Comment.findAllByTargetIdAndType", params);
	}

	@Override
	public Comment findLastByBlogIdAndCommentType(String blogId, CommentType type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("type", type.toString());
		
		return (Comment) sqlSession.selectOne("Comment.findLastByBlogIdAndCommentType", params);
	}


	@Override
	public Comment findByDisplayIdAndTargetIdAndType(long displayId, long targetId, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("displayId", displayId);
		params.put("targetId", targetId);
		params.put("type", type);
		
		return (Comment) sqlSession.selectOne("Comment.findByDisplayIdAndTargetIdAndType",params);
	}

	@Override
	public Comment findLast() {
		return (Comment) sqlSession.selectOne("Comment.findLast");
	}

}
