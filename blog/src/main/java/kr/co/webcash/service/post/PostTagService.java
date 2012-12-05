package kr.co.webcash.service.post;

import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.PostTag;

public interface PostTagService {

	void save(Post post);
	boolean save(PostTag postTag);
	void delete(Post post);
	void update(Post post);


}
