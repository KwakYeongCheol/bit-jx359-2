package kr.co.webcash.repository;

import kr.co.webcash.domain.Post;

public interface PostRepository {

	Post findLastPostByBlogId(String blogId);

	void insert(Post post);

}
