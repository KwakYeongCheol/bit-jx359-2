package kr.co.webcash.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Category;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostRepository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository{
	@Autowired private SqlSession sqlSession;
	@Autowired private PostRepository postRepository;
	
	public List<Category> wrap(List<Category> categoryList){
		for(Category category:categoryList){
			wrap(category);
		}
		return categoryList;
	}
	public Category wrap(Category category){
		
		addPostList(category);
		return category;
	}
	
	private void addPostList(Category category) {
		category.setPostList(postRepository.findAllByCategoryId(category.getId()));
	}
	
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
		return wrap( sqlSession.<Category>selectList("Category.findAllByBlogId", blogId));
	}

	@Override
	public Category findLastByBlogId(String blogId) {
		return wrap(sqlSession.<Category>selectOne("Category.findLastByBlogId", blogId));
	}


	@Override
	public Category findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("displayId", displayId);
		return wrap(sqlSession.<Category>selectOne("Category.findByBlogIdAndDisplayId", params));
	}

	@Override
	public long countByBlogId(String blogId) {
		return sqlSession.<Long>selectOne("Category.countByBlogId", blogId);
	}

	@Override
	public Category findLast() {
		return wrap(sqlSession.<Category>selectOne("Category.findLast"));
	}

	@Override
	public Category findLastByBlogIdAndOrderValue(String blogId) {
		return wrap(sqlSession.<Category>selectOne("Category.findLastByBlogIdAndOrderValue", blogId));
	}
}
