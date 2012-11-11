package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.blog.Blog;

public interface BlogRepository {

	void create(Blog blog);

	List<Blog> findAllByUserLoginId(String loginId);

	Blog findById(String id);

	void update(Blog blog);

	List<Blog> findAll();

	void updateTotalCount(Blog blog);
	
}
