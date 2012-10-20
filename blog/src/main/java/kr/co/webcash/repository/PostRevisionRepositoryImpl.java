package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.PostRevision;
import kr.co.webcash.domain.post.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRevisionRepositoryImpl implements PostRevisionRepository{
	
	@Autowired SqlMapClientTemplate template;

	@Override
	public PostRevision findLastRevisionByPostId(long postId) {
		return (PostRevision) template.queryForObject("PostRevision.findLastRevisionByPostId", postId);
	}

	@Override
	public boolean insert(PostRevision postRevision) {
		template.insert("PostRevision.insert", postRevision);
		return true;
	}

	@Override
	public boolean update(PostRevision postRevision) {
		template.update("PostRevision.update", postRevision);
		return true;
	}

	@Override
	public List<PostRevision> findAllByPost(Post post) {
		return template.queryForList("PostRevision.findAllByPostId", post.getId());
	}

	@Override
	public List<PostRevision> findAllByPostAndFromRevisionAndToRevision(Post post, long fromDisplayId, long toDisplayId) {
		Map params = new HashMap();
		params.put("postId", post.getId());
		params.put("fromDisplayId", fromDisplayId);
		params.put("toDisplayId", toDisplayId);
		
		return template.queryForList("PostRevision.findAllByPostAndFromRevisionAndToRevision", params);
	}

}
