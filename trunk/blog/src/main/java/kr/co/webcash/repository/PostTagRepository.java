package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.post.PostTag;

public interface PostTagRepository {

	boolean insert(PostTag postTag);

	List<PostTag> findAllByPostId(long postId);

}
