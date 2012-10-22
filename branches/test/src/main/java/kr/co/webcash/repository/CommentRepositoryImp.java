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
	public void delete(Comment comment) {		
		template.delete("Comment.delete",comment);
	}
	
	@Override
	public void update(Comment comment) {
		template.update("Comment.update",comment);
	}

	@Override
	public List<Comment> findAllByTargetIdAndType(long targetId, CommentType type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("targetId", targetId);
		params.put("type", type.toString());
		
		return template.queryForList("Comment.findAllByTargetIdAndType", params);
	}

//	@Override
//	public Comment findLastByTargetIdAndType(long targetId, String type) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("targetId", targetId);
//		params.put("type", type.toString());
//		
//		return  (Comment) template.queryForObject("Comment.findLastByTargetIdAndType", params);
//	}
	
	@Override
	public Comment findLastByBlogIdAndCommentType(String blogId, CommentType type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("type", type.toString());
		
		return (Comment) template.queryForObject("Comment.findLastByBlogIdAndCommentType", params);
	}


	@Override
	public Comment findByDisplayIdAndTargetIdAndType(long displayId, long targetId, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("displayId", displayId);
		params.put("targetId", targetId);
		params.put("type", type);
		
		return (Comment) template.queryForObject("Comment.findByDisplayIdAndTargetIdAndType",params);
	}

	@Override
	public Comment findLast() {
		return (Comment) template.queryForObject("Comment.findLast");
	}

}
