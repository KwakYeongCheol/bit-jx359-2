package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.post.scrap.Scrap;

public interface ScrapRepository {

	List<Scrap> findAllByPostId(long postId);
	List<Scrap> findAllByTargetPostId(long targetPostId);
	List<Scrap> findAllByTargetBlogId(String targetBlogId);

	void insert(Scrap scrap);
	
	Scrap findByPostIdAndTargetPostIdAndTargetPostRevisionId(long postId, long targetPostId, long targetPostRevisionId);


	int countByBlogId(String blogId);

}
