package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.blog.Blog;

public interface CategoryService {

	List<Category> listByBlogId(String blogId);

	long findLastDisplayIdByBlogId(String blogId);

	void save(Category category);
	void saveDefault(Blog blog);

	void update(Category category);

	void delete(Category category);

	Category findByBlogIdAndDisplayId(Category category);
	Category findByBlogIdAndDisplayId(String blogId, long displayId);


}
