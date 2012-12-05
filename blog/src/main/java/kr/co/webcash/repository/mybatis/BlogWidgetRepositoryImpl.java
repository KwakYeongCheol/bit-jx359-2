package kr.co.webcash.repository.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.webcash.domain.blog.BlogWidget;
import kr.co.webcash.repository.BlogWidgetRepository;

@Repository
public class BlogWidgetRepositoryImpl implements BlogWidgetRepository{
	
	@Autowired private SqlSession sqlSession;

	@Override
	public void insert(BlogWidget blogWidget) {
		sqlSession.insert("BlogWidget.insert", blogWidget);
	}
	
	@Override
	public void update(BlogWidget blogWidget) {
		sqlSession.update("BlogWidget.update", blogWidget);
	}
	
	@Override
	public BlogWidget findByBlogId(String blogId) {
		return sqlSession.<BlogWidget>selectOne("BlogWidget.findByBlogId", blogId);
	}
}
