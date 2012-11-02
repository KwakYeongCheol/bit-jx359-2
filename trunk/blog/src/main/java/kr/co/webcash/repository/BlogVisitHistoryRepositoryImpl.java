package kr.co.webcash.repository;

import java.util.Map;

import kr.co.webcash.domain.BlogVisitHistory;

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
}
