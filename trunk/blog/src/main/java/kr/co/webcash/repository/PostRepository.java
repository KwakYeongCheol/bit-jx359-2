package kr.co.webcash.repository;

import java.util.List;
import java.util.Map;

import kr.co.webcash.domain.post.Post;

public interface PostRepository {
	void insert(Post post);
	void update(Post post);
	void delete(Post post);

	List<Post> findAllByCategoryId(long categoryId);
	List<Post> findAllByBlogId(String blogId);
	List<Post> findAllByBlogIdAndPostMetadataParams(String blogId, Map postMetadataParams);
	
	Post findLastPostByBlogId(String blogId);
	Post findByBlogIdAndDisplayId(String blogId, long displayId);
	
	Post findByCategoryIdAndDisplayId(long categoryId, long displayId);
	Post findLastByBlogId(String blogId);
	Post findLastByCategoryId(long categoryId);
//	Post findLastByBlogIdAndCategoryId(String blogId, long categoryId);
	Post findLast();
}
