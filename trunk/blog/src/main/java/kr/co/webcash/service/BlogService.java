package kr.co.webcash.service;

import kr.co.webcash.domain.Blog;

public interface BlogService {

	void createBlog(Blog blog);

	Blog findByUserLoginId(String loginId);

}
