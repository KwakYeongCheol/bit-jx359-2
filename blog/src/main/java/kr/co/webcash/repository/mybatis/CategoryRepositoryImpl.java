package kr.co.webcash.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Category;
import kr.co.webcash.repository.CategoryRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository{
	@Autowired private SqlSession sqlSession;
	
	@Override
	public void insert(Category category) {
		sqlSession.insert("Category.insert", category);
	}
	
	@Override
	public void update(Category category) {
		sqlSession.update("Category.update",category);
	}
	
	@Override
	public void delete(Category category) {
		sqlSession.delete("Category.delete", category);
	}

	@Override
	public List<Category> findAllByBlogId(String blogId) {
		return sqlSession.selectList("Category.findAllByBlogId", blogId);
	}

	@Override
	public Category findLastByBlogId(String blogId) {
		return sqlSession.<Category>selectOne("Category.findLastByBlogId", blogId);
	}


	@Override
	public Category findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("displayId", displayId);
		return sqlSession.<Category>selectOne("Category.findByBlogIdAndDisplayId", params);
	}

	@Override
	public long countByBlogId(String blogId) {
		return sqlSession.<Long>selectOne("Category.countByBlogId", blogId);
	}

	@Override
	public Category findLast() {
		return sqlSession.<Category>selectOne("Category.findLast");
	}

	@Override
	public Category findLastByBlogIdAndOrderValue(String blogId) {
		return sqlSession.<Category>selectOne("Category.findLastByBlogIdAndOrderValue", blogId);
	}
}
