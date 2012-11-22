package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Page;
import kr.co.webcash.domain.post.Post;

public interface PostRepository {
	void insert(Post post);
	void update(Post post);
	void delete(Post post);

	int count();
	
	int countByTagList(List<String> tagList);
	
	int countByBlogId(String blogId);
	int countPublicByBlogId(String blogId);
	
	int countByCategoryId(long categoryId);
	
	Post findById(long id);
	Post findLastPostByBlogId(String blogId);
	Post findByBlogIdAndDisplayId(String blogId, long displayId);
	Post findByCategoryIdAndDisplayId(long categoryId, long displayId);
	Post findLastByBlogId(String blogId);
	Post findLastByCategoryId(long categoryId);
	Post findLast();
	
	List<Post> findAll();
	List<Post> findAllByPage(Page page);
	List<Post> findAllByCategoryId(long categoryId);
	List<Post> findAllByCategoryIdAndOffsetAndLimit(long categoryId, int offset, int limit);
	List<Post> findAllByBlogId(String blogId);
	List<Post> findAllByQuery(String query);
	List<Post> findAllByBlogIdAndPage(String blogId, int offset, int limit);
	List<Post> findAllPublicByBlogIdAndPage(String blogId, int offset, int limit);
	
	List<Post> findAllTempByBlogId(String blogId);
	List<Post> findAllByTagListAndPage(List<String> tagList, int offset, int limit);
}
