package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImp implements CommentRepository {

	@Autowired SqlMapClientTemplate template;
	
	@Override
	public void save(Comment comment) {
		template.insert("Comment.insert",comment);
	}

	@Override
	public List<Comment> findAllByBlogIdAndTargetIdAndType(String blogId, long targetId, CommentType type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("targetId", targetId);
		params.put("type", type.toString());
		
		return template.queryForList("Comment.findAllByBlogIdAndTargetIdAndType", params);
	}

	@Override
	public Comment findLastByBlogIdAndTargetIdAndType(String blogId, long targetId, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("targetId", targetId);
		params.put("type", type.toString());
		
		return  (Comment) template.queryForObject("Comment.findLastIdByBlogIdAndTargetIdAndType", params);
	}

	@Override
	public void delete(Comment comment) {		
		template.delete("Comment.delete",comment);
	}

	@Override
	public void update(Comment comment) {
		template.update("Comment.update",comment);
	}

	@Override
	public Comment findByIdAndBlogIdAndTargetIdAndType(long id, String blogId, long targetId, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("blogId", blogId);
		params.put("targetId", targetId);
		params.put("type", type);
		
		return (Comment) template.queryForObject("Comment.findByIdAndBlogIdAndTargetIdAndType",params);
	}
}
