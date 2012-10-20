package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.post.Post;

public interface PostService {
	void save(Post post);
	void update(Post post);
	void delete(Post post);

	long findLastDisplayIdByBlogId(String blogId);
	
	Post findById(long id);
	Post findByBlogIdAndDisplayId(String blogId, long displayId);

	List<Post> listByBlogIdAndCategoryDisplayId(String blogId, long categoryDisplayId);
	List<Post> listPublic(String blogId);
	List<Post> listAll(String blogId);
	
	List<Post> search(String query);
}
