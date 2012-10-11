package kr.co.webcash.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository{
	@Autowired SqlMapClientTemplate template;
	@Override
	public void insert(Category category) {
		template.insert("Category.insert", category);
	}

	@Override
	public List<Category> findAllByBlogId(String blogId) {
		return template.queryForList("Category.findAllByBlogId", blogId);
	}

	@Override
	public Category findLastPostByBlogId(String blogId) {
		return (Category) template.queryForObject("Category.findLastPostByBlogId", blogId);
	}

	@Override
	public void update(Category category) {
		template.update("Category.update",category);
	}

	@Override
	public void delete(Category category) {
		template.delete("Category.delete", category);
	}

	@Override
	public Category findByBlogIdAndDisplayId(String blogId, long displayId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blogId", blogId);
		params.put("displayId", displayId);
		return (Category) template.queryForObject("Category.findByBlogIdAndDisplayId", params);
	}

	@Override
	public long findAllCountByBlogId(String blogId) {
		return (Long) template.queryForObject("Category.findAllCountByIdAndBlogId", blogId);
	}

	@Override
	public Category findLast() {
		return (Category) template.queryForObject("Category.findLast");
	}

}
