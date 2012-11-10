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
	
	@Autowired SqlSession sqlSession;
	
	@Override
	public void insert(Category category) {
		sqlSession.insert("Category.insert", category);
	}

	@Override
	public List<Category> findAllByBlogId(String blogId) {
		return sqlSession.selectList("Category.findAllByBlogId", blogId);
	}

	@Override
	public Category findLastPostByBlogId(String blogId) {
		return (Category) sqlSession.selectOne("Category.findLastPostByBlogId", blogId);
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
	public Category findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("displayId", displayId);
		return (Category) sqlSession.selectOne("Category.findByBlogIdAndDisplayId", params);
	}

	@Override
	public long findAllCountByBlogId(String blogId) {
		return (Long) sqlSession.selectOne("Category.findAllCountByIdAndBlogId", blogId);
	}

	@Override
	public Category findLast() {
		return (Category) sqlSession.selectOne("Category.findLast");
	}

}
