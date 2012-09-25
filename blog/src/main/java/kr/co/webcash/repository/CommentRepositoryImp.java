package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import kr.co.webcash.domain.Comment;
import kr.co.webcash.domain.CommentType;

@Repository
public class CommentRepositoryImp implements CommentRepository {

	@Autowired SqlMapClientTemplate template;
	
	@Override
	public void save(Comment comment) {
		template.insert("Comment.insert",comment);
	}

	@Override
	public List<Comment> findAllByBlogIdAndTargetIdAndType(String blogId, String targetId, CommentType type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("blogId", blogId);
		params.put("targetId", targetId);
		params.put("type", type.toString());
		
		return template.queryForList("Comment.findAllByBlogIdAndTargetIdAndType", params);
	}

	@Override
	public Comment findLastByBlogIdAndTargetIdAndType(String blogId, String targetId, String type) {
		Map<String, String> params = new HashMap<String, String>();
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
	public Comment findByIdAndBlogIdAndTargetIdAndType(String id, String blogId, String targetId, String type) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("blogId", blogId);
		params.put("targetId", targetId);
		params.put("type", type);
		
		return (Comment) template.queryForObject("Comment.findByIdAndBlogIdAndTargetIdAndType",params);
	}
}
