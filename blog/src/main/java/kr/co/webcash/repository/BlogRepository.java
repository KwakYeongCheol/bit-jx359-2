package kr.co.webcash.repository;

import kr.co.webcash.domain.Blog;

public interface BlogRepository {

	void create(Blog blog);

	Blog findByUserLoginId(String loginId);
	
}
