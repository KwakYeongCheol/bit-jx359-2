package kr.co.webcash.service.post;

import java.util.List;

import kr.co.webcash.domain.post.Post;
import kr.co.webcash.domain.post.PostRevision;
import kr.co.webcash.repository.PostRevisionRepository;

public interface PostRevisionService {
	void init(PostService postService, PostRevisionRepository postRevisionRepository);
	boolean save(Post post);

	PostRevision currentRevision(Post post);
	PostRevision get(long postId, long postRevisionDisplayId);
	
	List<PostRevision> list(Post post);
	void delete(Post post);
}
