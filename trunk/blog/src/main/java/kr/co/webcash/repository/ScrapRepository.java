package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Scrap;

public interface ScrapRepository {

	List<Scrap> findAllByPostId(long postId);

	void insert(Scrap scrap);

	Scrap findByPostIdAndTargetBlogIdAndTargetPostDisplayId(long postId, String targetBlogId, long targetPostDisplayId);

}
