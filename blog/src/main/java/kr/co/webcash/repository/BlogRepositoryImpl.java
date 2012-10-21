package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Blog;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepositoryImpl implements BlogRepository{

	@Autowired private SqlSession sqlSession;
	
	@Override
	public void create(Blog blog) {
		sqlSession.insert("Blog.create", blog);
	}

	@Override
	public List<Blog> findAllByUserLoginId(String loginId) {
		return sqlSession.selectList("Blog.findAllByUserLoginId", loginId);
	}

	@Override
	public Blog findById(String id) {
		return (Blog)sqlSession.selectOne("Blog.findById", id);
	}

	@Override
	public void update(Blog blog) {
		sqlSession.update("Blog.update", blog);
	}

	@Override
	public List<Blog> findAll() {
		return sqlSession.selectList("Blog.findAll");
	}

}
