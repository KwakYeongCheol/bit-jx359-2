package kr.co.webcash.repository.mybatis;

import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.blog.BlogVisitHistory;
import kr.co.webcash.repository.BlogVisitHistoryRepository;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogVisitHistoryRepositoryImpl implements BlogVisitHistoryRepository{
	
	@Autowired private SqlSession sqlSession;

	@Override
	public void insert(BlogVisitHistory blogVisitHistory) {
		sqlSession.insert("BlogVisitHistory.insert", blogVisitHistory);
	}

	@Override
	public long countByBlogIdAndFromStartDateToEndDate(Map params) {
		return sqlSession.selectOne("BlogVisitHistory.countByBlogIdAndFromStartDateToEndDate", params);
	}

	@Override
	public BlogVisitHistory findByBlogIdAndConnectIPAddressAndFromStartDateToEndDate(Map<String, Object> params) {
		return sqlSession.selectOne("BlogVisitHistory.findByBlogIdAndConnectIPAddressAndFromStartDateToEndDate", params);
	}

	@Override
	public List<String> select(String blogId) {
		return sqlSession.selectList("BlogVisitHistory.select", blogId);
	}

	@Override
	public int countByBlogId(String blogId) {
		return sqlSession.<Integer>selectOne("BlogVisitHistory.countByBlogId", blogId);
	}

	@Override
	public List<BlogVisitHistory> findAllByBlogIdAndOffsetAndLimit(String blogId, int offset, int limit) {
		return sqlSession.<BlogVisitHistory>selectList("BlogVisitHistory.findAllByBlogId", blogId, new RowBounds(offset, limit));
	}
}
