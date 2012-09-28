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
	public Category findByIdAndBlogId(String id, String blogId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		param.put("blogId", blogId);
		return (Category) template.queryForObject("Category.findByIdAndBlogId", param);
	}

	@Override
	public long findAllCountByBlogId(String blogId) {
		return (Long) template.queryForObject("Category.findAllCountByIdAndBlogId", blogId);
	}

}
