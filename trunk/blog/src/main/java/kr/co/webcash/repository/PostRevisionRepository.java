package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.PostRevision;
import kr.co.webcash.domain.post.Post;

public interface PostRevisionRepository {

	PostRevision findByPostIdAndDisplayId(long postId, long displayId);
	PostRevision findLastRevisionByPostId(long postId);

	boolean insert(PostRevision postRevision);

	boolean update(PostRevision postRevision);

	List<PostRevision> findAllByPost(Post post);
	List<PostRevision> findAllByPostAndFromRevisionAndToRevision(Post post, long fromDisplayId, long toDisplayId);


}
