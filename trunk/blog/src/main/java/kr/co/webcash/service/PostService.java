package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Post;

public interface PostService {

	long findLastIdByBlogId(String blogId);

	void save(Post post);

	List<Post> listByBlogId(String blogId);

	Post findByIdAndBlogId(long id, String blogId);

	void update(Post post);

	void delete(Post post);

	List<Post> listByBlogIdAndCategoryId(String blogId, long categoryId);

}
