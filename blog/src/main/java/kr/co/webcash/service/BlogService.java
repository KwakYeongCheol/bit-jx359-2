package kr.co.webcash.service;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.User;

public interface BlogService {

	void createBlog(Blog blog);

	Blog findByUserLoginId(String loginId);

	boolean isExist(String id);

	void modify(Blog blog);

	Blog findById(String blogId);

	boolean isAdmin(String blogId, User user);

}
