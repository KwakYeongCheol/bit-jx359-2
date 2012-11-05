package kr.co.webcash.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.webcash.domain.post.PostTag;

@Repository
public class PostTagRepositoryImpl implements PostTagRepository {
	
	@Autowired private SqlSession session;

	@Override
	public boolean insert(PostTag postTag) {
		session.insert("PostTag.insert", postTag);
		return true;
	}

	@Override
	public List<PostTag> findAllByPostId(long postId) {
		return session.selectList("PostTag.findAllByPostId", postId);
	}

}