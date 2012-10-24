package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.User;

public interface BlogService {

	void createBlog(Blog blog);

	List<Blog> findAll();
	List<Blog> findAllByUserLoginId(String loginId);

	boolean isExist(String id);

	void modify(Blog blog);

	Blog findById(String blogId);

	boolean isAdmin(String blogId, User user);

	void addVisitCount(Blog blog);

}
