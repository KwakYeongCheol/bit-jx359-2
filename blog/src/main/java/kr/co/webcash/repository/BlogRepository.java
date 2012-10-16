package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Blog;

public interface BlogRepository {

	void create(Blog blog);

	List<Blog> findAllByUserLoginId(String loginId);

	Blog findById(String id);

	void update(Blog blog);
	
}
