package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.PostRevision;
import kr.co.webcash.domain.post.Post;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostRevisionRepositoryImpl implements PostRevisionRepository{
	
	@Autowired SqlSession sqlSession;

	@Override
	public PostRevision findLastRevisionByPostId(long postId) {
		return (PostRevision) sqlSession.selectOne("PostRevision.findLastRevisionByPostId", postId);
	}

	@Override
	public boolean insert(PostRevision postRevision) {
		sqlSession.insert("PostRevision.insert", postRevision);
		return true;
	}

	@Override
	public boolean update(PostRevision postRevision) {
		sqlSession.update("PostRevision.update", postRevision);
		return true;
	}

	@Override
	public List<PostRevision> findAllByPost(Post post) {
		return sqlSession.selectList("PostRevision.findAllByPostId", post.getId());
	}

	@Override
	public List<PostRevision> findAllByPostAndFromRevisionAndToRevision(Post post, long fromDisplayId, long toDisplayId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("postId", post.getId());
		params.put("fromDisplayId", fromDisplayId);
		params.put("toDisplayId", toDisplayId);
		
		return sqlSession.selectList("PostRevision.findAllByPostAndFromRevisionAndToRevision", params);
	}

}
