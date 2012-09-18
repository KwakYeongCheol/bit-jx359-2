package kr.co.webcash.service;

import kr.co.webcash.domain.Post;

public interface PostService {

	int findLastIdByBlogId(String blogId);

	void save(Post post);

}
