package kr.co.webcash.service.blog;

import java.util.List;

import kr.co.webcash.domain.blog.Blog;
import kr.co.webcash.domain.blog.BlogVisitHistory;
import kr.co.webcash.domain.user.User;

public interface BlogService {

	boolean createBlog(Blog blog);

	List<Blog> findAll();
	List<Blog> findAllByUserLoginId(String loginId);

	boolean isExist(String id);

	void modify(Blog blog);

	Blog findById(String blogId);

	boolean isAdmin(String blogId, User user);

	void addVisitCount(BlogVisitHistory blogVisitHistory);
}
