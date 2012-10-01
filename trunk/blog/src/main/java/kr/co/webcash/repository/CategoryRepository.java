package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Category;

public interface CategoryRepository {

	void insert(Category category);

	List<Category> findAllByBlogId(String blogId);

	Category findLastPostByBlogId(String blogId);

	void update(Category category);

	void delete(Category category);

	Category findByIdAndBlogId(long id, String blogId);

	long findAllCountByBlogId(String blogId);
	
}
