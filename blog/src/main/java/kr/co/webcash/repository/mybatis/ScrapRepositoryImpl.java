package kr.co.webcash.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.post.scrap.Scrap;
import kr.co.webcash.repository.BlogRepository;
import kr.co.webcash.repository.ScrapRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapRepositoryImpl implements ScrapRepository{
	@Autowired private SqlSession sqlSession;
	
	@Autowired private BlogRepository blogRepository;
	
	@Override
	public void insert(Scrap scrap) {
		sqlSession.insert("Scrap.insert", scrap);
	}

	@Override
	public Scrap findByPostIdAndTargetPostIdAndTargetPostRevisionId(long postId, long targetPostId, long targetPostRevisionId) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("postId", postId);
		params.put("targetPostId", targetPostId);
		params.put("targetPostRevisionId", targetPostRevisionId);
		
		return sqlSession.<Scrap>selectOne("Scrap.findByPostIdAndTargetPostIdAndTargetPostRevisionId", params);
	}

	@Override
	public List<Scrap> findAllByPostId(long postId) {
		List<Scrap> scrapList = sqlSession.selectList("Scrap.findAllByPostId", postId);
		
		return scrapList;
	}
	
	@Override
	public List<Scrap> findAllByTargetBlogId(String blogId) {
		return sqlSession.<Scrap>selectList("Scrap.findAllByTargetBlogId", blogId);
	}

	@Override
	public int countByBlogId(String blogId) {
		return sqlSession.<Integer>selectOne("Scrap.countByBlogId", blogId);
	}

}
