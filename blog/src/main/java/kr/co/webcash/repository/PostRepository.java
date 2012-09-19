package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Post;

public interface PostRepository {

	Post findLastPostByBlogId(String blogId);

	void insert(Post post);

	List<Post> findAllByBlogId(String blogId);

}
