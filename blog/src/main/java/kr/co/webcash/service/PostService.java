package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Post;

public interface PostService {

	int findLastIdByBlogId(String blogId);

	void save(Post post);

	List<Post> listByBlogId(String blogId);

	Post findByIdAndBlogId(String id, String blogId);

	void update(Post post);

}
