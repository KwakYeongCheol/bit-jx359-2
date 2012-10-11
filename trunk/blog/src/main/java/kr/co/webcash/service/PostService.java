package kr.co.webcash.service;

import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.post.Post;

public interface PostService {
	void save(Post post);
	void update(Post post);
	void delete(Post post);

	long findLastDisplayIdByBlogId(String blogId);
//	Post findLastByBlogId(String blogId);
	
//	long findLastIdByBlogId(String blogId);

//	List<Post> listByBlogId(String blogId);

//	Post findByIdAndBlogId(long id, String blogId);
	
	Post findByBlogIdAndDisplayId(String blogId, long displayId);

	List<Post> listByBlogIdAndCategoryDisplayId(String blogId, long categoryDisplayId);

	List<Post> listPublic(String blogId);

	List<Post> listAll(String blogId);


}
