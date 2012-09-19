package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Post;

public interface PostRepository {

	Post findLastPostByBlogId(String blogId);

	void insert(Post post);

	List<Post> findAllByBlogId(String blogId);

	Post findByIdAndBlogId(String id, String blogId);

	void update(Post post);

	void delete(Post post);

}
