package kr.co.webcash.repository;

import kr.co.webcash.domain.post.PostMetadata;

public interface PostMetadataRepository {

	PostMetadata findByPostId(long postId);

	void insert(PostMetadata metadata);

}
