package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Trackback;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrackbackRepositoryImpl implements TrackbackRepository {
	@Autowired private SqlSession sqlSession;
	
	@Override
	public void insert(Trackback trackback) {
		sqlSession.insert("Trackback.insert", trackback);
	}
	
	@Override
	public List<Trackback> findAllByPostId(long postId) {
		return sqlSession.selectList("Trackback.findAllByPostId",postId);
	}

}
