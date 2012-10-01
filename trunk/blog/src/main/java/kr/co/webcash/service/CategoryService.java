package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Category;

public interface CategoryService {

	List<Category> listByBlogId(String blogId);

	long findLastIdByBlogId(String blogId);

	void save(Category category);

	void update(Category category);

	void delete(Category category);

	Category findByIdAndBlogId(long id, String blogId);

}
