package kr.co.webcash.repository.mybatis;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.repository.BlogRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepositoryImpl implements BlogRepository{

	@Autowired private SqlSession sqlSession;
	
	@Override
	public void create(Blog blog) {
		try{
			sqlSession.insert("Blog.create", blog);
		}catch(DataAccessException e){
			throw e;
		}
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

	@Override
	public void updateTotalCount(Blog blog) {
		sqlSession.update("Blog.updateTotalCount", blog);
	}

}
