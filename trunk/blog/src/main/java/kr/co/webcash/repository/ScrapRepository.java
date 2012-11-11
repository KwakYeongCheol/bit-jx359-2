package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Scrap;

public interface ScrapRepository {

	List<Scrap> findAllByPostId(long postId);

	void insert(Scrap scrap);
	
	Scrap findByPostIdAndTargetPostIdAndTargetPostRevisionId(long postId, long targetPostId, long targetPostRevisionId);

	List<Scrap> findAllByTargetBlogId(String blogId);
}
